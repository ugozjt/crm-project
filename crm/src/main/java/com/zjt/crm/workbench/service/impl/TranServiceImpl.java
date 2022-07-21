package com.zjt.crm.workbench.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjt.crm.commons.utils.DateUtils;
import com.zjt.crm.commons.utils.UUIDUtils;
import com.zjt.crm.settings.pojo.User;
import com.zjt.crm.workbench.mapper.CustomerMapper;
import com.zjt.crm.workbench.mapper.TranHistoryMapper;
import com.zjt.crm.workbench.pojo.Customer;
import com.zjt.crm.workbench.pojo.FunnelVO;
import com.zjt.crm.workbench.pojo.Tran;
import com.zjt.crm.workbench.pojo.TranHistory;
import com.zjt.crm.workbench.service.TranService;
import com.zjt.crm.workbench.mapper.TranMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
* @author ugozjt
* @description 针对表【tbl_tran】的数据库操作Service实现
* @createDate 2022-07-17 02:38:39
*/
@Service
public class TranServiceImpl extends ServiceImpl<TranMapper, Tran>
    implements TranService{

    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private TranMapper tranMapper;
    @Autowired
    private TranHistoryMapper tranHistoryMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveTran(Tran tran, User user) {
        //前台传递的实际是客户名，不是客户id
        String customerName = tran.getCustomerId();
        Customer customer = customerMapper.selectCustomerByCustomerName(customerName);
        //如果客户不存在,创建客户
        if (null == customer) {
            customer = new Customer();
            customer.setId(UUIDUtils.getUUID());
            customer.setCreateBy(user.getId());
            customer.setCreateTime(DateUtils.formatDateTime(new Date()));
            customer.setName(customerName);
            customer.setOwner(user.getId());
            customerMapper.insertCustomer(customer);
        }
        //添加交易
        tran.setId(UUIDUtils.getUUID());
        tran.setCustomerId(customer.getId());
        tran.setCreateBy(user.getId());
        tran.setCreateTime(DateUtils.formatDateTime(new Date()));
        tranMapper.insertTran(tran);

        //添加交易历史记录 : 因为交易有阶段
        TranHistory tranHistory = new TranHistory();
        tranHistory.setTranId(tran.getId());
        tranHistory.setId(UUIDUtils.getUUID());
        tranHistory.setCreateBy(user.getId());
        tranHistory.setCreateTime(DateUtils.formatDateTime(new Date()));
        tranHistory.setMoney(tran.getMoney());
        tranHistory.setStage(tran.getStage());
        tranHistory.setExpectedDate(tran.getExpectedDate());
        tranHistoryMapper.saveTranHistory(tranHistory);

    }

    @Override
    public IPage<Tran> selectTranForPageByCondition(Page<Tran> page, Tran tran) {
        return tranMapper.selectTranForPageByCondition(page, tran);
    }

    @Override
    public Tran selectTranForDetailById(String tranId) {
        return tranMapper.selectTranForDetailById(tranId);
    }

    @Override
    public List<FunnelVO> getTranCountGroupByStage() {

        return tranMapper.getTranCountGroupByStage();
    }
}




