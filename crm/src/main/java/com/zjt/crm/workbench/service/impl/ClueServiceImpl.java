package com.zjt.crm.workbench.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjt.crm.commons.utils.DateUtils;
import com.zjt.crm.commons.utils.UUIDUtils;
import com.zjt.crm.settings.pojo.User;
import com.zjt.crm.workbench.mapper.*;
import com.zjt.crm.workbench.pojo.*;
import com.zjt.crm.workbench.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author ugozjt
 * @description 针对表【tbl_clue】的数据库操作Service实现
 * @createDate 2022-07-15 23:42:52
 */
@Service
public class ClueServiceImpl extends ServiceImpl<ClueMapper, Clue>
        implements ClueService {

    @Autowired
    private ClueMapper clueMapper;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private ContactsMapper contactsMapper;
    @Autowired
    private ClueRemarkMapper clueRemarkMapper;
    @Autowired
    private CustomerRemarkMapper customerRemarkMapper;
    @Autowired
    private ContactsRemarkMapper contactsRemarkMapper;
    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private ContactsActivityRelationMapper contactsActivityRelationMapper;
    @Autowired
    private TranMapper tranMapper;
    @Autowired
    private TranRemarkMapper tranRemarkMapper;
    @Autowired
    private ClueActivityRelationMapper clueActivityRelationMapper;

    @Override
    public int insertClue(Clue clue) {

        return clueMapper.insertClue(clue);
    }

    @Override
    public Clue selectClueForDetailByClueId(String id) {

        return clueMapper.selectClueForDetailByClueId(id);

    }

    @Override
    public IPage<Clue> selectClueForPageByCondition(Page<Clue> page, Clue clue) {
        return clueMapper.selectClueForPageByCondition(page, clue);
    }

    /**
     * rollbackFor = Exception.class : 表示任何异常都回滚，不加此属性只在运行时异常及Error才回滚
     * 线索转换
     * @param map clueId, 如果要创建交易的交易信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveClueConvert(Map<String, Object> map) {

        //获取clue信息
        Clue clue = clueMapper.selectClueByIdWithOriginalField(map.get("clueId").toString());
        User user = (User) map.get("user");

        //1、把该线索中有关公司的信息转换到客户表中
        Customer customer = new Customer(UUIDUtils.getUUID(), user.getId(), clue.getCompany(), clue.getWebsite(),
                clue.getPhone(), user.getId(), DateUtils.formatDateTime(new Date()), null,
                null, clue.getContactSummary(), clue.getNextContactTime(),
                clue.getDescription(), clue.getAddress());
        customerMapper.insertCustomer(customer);

        //2、把该线索中有关个人的信息转换到联系人表中
        Contacts contacts = new Contacts(UUIDUtils.getUUID(), user.getId(), clue.getSource(), customer.getId(),
                clue.getFullname(), clue.getAppellation(), clue.getEmail(), clue.getMphone(),
                clue.getJob(), user.getId(), DateUtils.formatDateTime(new Date()), null,
                null, clue.getDescription(), clue.getContactSummary(), clue.getNextContactTime(),
                clue.getAddress());
        contactsMapper.insertContacts(contacts);

        //3、把该线索中所有备注信息转换到客户备注表一份
        //4、把该线索中所有备注信息转换到联系人备注表中一份
        //3.1&4.1、查询此线索的全部备注信息
        List<ClueRemark> clueRemarks = clueRemarkMapper.selectClueRemarksByClueIdWithOriginalField(clue.getId());
        //线索备注不为空
        if (null != clueRemarks && 0 < clueRemarks.size()) {
            //3.2、线索备注循环替换为顾客备注
            ArrayList<CustomerRemark> customerRemarks = new ArrayList<>(clueRemarks.size());
            CustomerRemark customerRemark = null;
            for (ClueRemark clueRemark : clueRemarks) {
                customerRemark = new CustomerRemark(UUIDUtils.getUUID(), clueRemark.getNoteContent(),
                        clueRemark.getCreateBy(), clueRemark.getCreateTime(), clueRemark.getEditBy(),
                        clueRemark.getEditTime(), clueRemark.getEditFlag(), customer.getId());
                customerRemarks.add(customerRemark);
            }
            customerRemarkMapper.insertRemarksByClueRemarks(customerRemarks);

            //4.2、线索备注循环替换为联系人备注
            ArrayList<ContactsRemark> contactsRemarks = new ArrayList<>(clueRemarks.size());
            ContactsRemark contactsRemark = null;
            for (ClueRemark clueRemark : clueRemarks) {
                contactsRemark = new ContactsRemark(UUIDUtils.getUUID(), clueRemark.getNoteContent(),
                        clueRemark.getCreateBy(), clueRemark.getCreateTime(), clueRemark.getEditBy(),
                        clueRemark.getEditTime(), clueRemark.getEditFlag(), contacts.getId());
                contactsRemarks.add(contactsRemark);
            }
            contactsRemarkMapper.insertContactsRemarkByClueRemark(contactsRemarks);

        }

        //5、把该线索和市场活动的关系转换到联系人和市场活动的关系表中
        //5.1、查询此线索的所有市场活动
        List<Activity> activities = activityMapper.selectActivitiesForClueDetailByClueId(clue.getId());
        if (activities.size() > 0) {
            //5.2、将线索-市场活动关系 转换为 联系人-市场活动关系
            ArrayList<ContactsActivityRelation> contactsActivityRelations = new ArrayList<>(activities.size());
            ContactsActivityRelation contactsActivityRelation = null;
            for (Activity activity : activities) {
                contactsActivityRelation = new ContactsActivityRelation();
                contactsActivityRelation.setId(UUIDUtils.getUUID());
                contactsActivityRelation.setActivityId(activity.getId());
                contactsActivityRelation.setContactsId(contacts.getId());
                contactsActivityRelations.add(contactsActivityRelation);
            }
            contactsActivityRelationMapper.insertContactsActivityRelationByClueActivityRelation(contactsActivityRelations);
        }

        //6、如果需要创建交易，往交易表中添加一条记录，并且将该线索中所有备注转换到交易备注表中
        if ("true".equals(map.get("isCreateTran").toString())) {
            //6.1、往交易表中添加一条记录
            Tran tran = new Tran();
            tran.setId(UUIDUtils.getUUID());
            tran.setActivityId(map.get("activityId").toString());
            tran.setContactsId(contacts.getId());
            tran.setCreateTime(DateUtils.formatDateTime(new Date()));
            tran.setCreateBy(user.getId());
            tran.setMoney(map.get("money").toString());
            tran.setName(map.get("name").toString());
            tran.setExpectedDate(map.get("expectedDate").toString());
            tran.setStage(map.get("stage").toString());
            tran.setOwner(user.getId());
            tran.setCustomerId(customer.getId());
            tranMapper.insertTran(tran);

            //6.2、并且将该线索中所有备注转换到交易备注表中
            if (null != clueRemarks && 0 < clueRemarks.size()) {
                ArrayList<TranRemark> tranRemarks = new ArrayList<>(clueRemarks.size());
                TranRemark tranRemark = null;
                for (ClueRemark clueRemark : clueRemarks) {
                    tranRemark = new TranRemark(UUIDUtils.getUUID(), clueRemark.getNoteContent(),
                            clueRemark.getCreateBy(), clueRemark.getCreateTime(), clueRemark.getEditBy(),
                            clueRemark.getEditTime(), clueRemark.getEditFlag(), tran.getId());
                    tranRemarks.add(tranRemark);
                }
                tranRemarkMapper.insertTranRemarkByClueRemark(tranRemarks);
            }
        }

        //7、删除该线索下所有的备注，删除该线索和市场活动的关联信息，删除该线索
        clueRemarkMapper.deleteClueRemarksByClueId(clue.getId());
        clueActivityRelationMapper.deleteClueActivityRelationsByClueId(clue.getId());
        clueMapper.deleteClueByClueId(clue.getId());

    }
}




