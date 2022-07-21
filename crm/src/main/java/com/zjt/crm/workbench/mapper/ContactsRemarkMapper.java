package com.zjt.crm.workbench.mapper;

import com.zjt.crm.workbench.pojo.ContactsRemark;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author ugozjt
* @description 针对表【tbl_contacts_remark】的数据库操作Mapper
* @createDate 2022-07-17 01:55:08
* @Entity com.zjt.crm.workbench.pojo.ContactsRemark
*/
@Repository
public interface ContactsRemarkMapper extends BaseMapper<ContactsRemark> {

    /**
     * 将线索备注转换为联系人备注
     * @param contactsRemarks contactsRemarks
     * @return 添加记录数
     */
    int insertContactsRemarkByClueRemark(@Param("contactsRemarks") List<ContactsRemark> contactsRemarks);

}




