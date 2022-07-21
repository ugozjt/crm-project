package com.zjt.crm.workbench.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjt.crm.workbench.pojo.Contacts;
import com.zjt.crm.workbench.service.ContactsService;
import com.zjt.crm.workbench.mapper.ContactsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author ugozjt
* @description 针对表【tbl_contacts】的数据库操作Service实现
* @createDate 2022-07-16 23:56:12
*/
@Service
public class ContactsServiceImpl extends ServiceImpl<ContactsMapper, Contacts>
    implements ContactsService{

    @Autowired
    private ContactsMapper contactsMapper;

    @Override
    public List<Contacts> selectContactsByContactsName(String contactsName) {

        return contactsMapper.selectContactsByContactsName(contactsName);

    }
}




