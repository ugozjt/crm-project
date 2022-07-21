package com.zjt.crm.workbench.service;

import com.zjt.crm.workbench.pojo.Contacts;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author ugozjt
* @description 针对表【tbl_contacts】的数据库操作Service
* @createDate 2022-07-16 23:56:12
*/
public interface ContactsService extends IService<Contacts> {

    List<Contacts> selectContactsByContactsName(String contactsName);

}
