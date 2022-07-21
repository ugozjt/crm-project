package com.zjt.crm.workbench.mapper;

import com.zjt.crm.workbench.pojo.Contacts;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author ugozjt
* @description 针对表【tbl_contacts】的数据库操作Mapper
* @createDate 2022-07-16 23:56:12
* @Entity com.zjt.crm.workbench.pojo.Contacts
*/
@Repository
public interface ContactsMapper extends BaseMapper<Contacts> {


    /**
     * 添加联系人
     * @param contacts
     * @return
     */
    int insertContacts(Contacts contacts);

    /**
     * 根据联系人名称模糊查询联系人信息
     * @param contactsName 联系人名称
     * @return List<Contacts>
     */
    List<Contacts> selectContactsByContactsName(String contactsName);

}




