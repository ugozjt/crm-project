package com.zjt.crm.workbench.mapper;

import com.zjt.crm.workbench.pojo.ContactsActivityRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author ugozjt
* @description 针对表【tbl_contacts_activity_relation】的数据库操作Mapper
* @createDate 2022-07-17 02:23:00
* @Entity com.zjt.crm.workbench.pojo.ContactsActivityRelation
*/
@Repository
public interface ContactsActivityRelationMapper extends BaseMapper<ContactsActivityRelation> {

    /**
     * 将线索-市场活动关系 转换为 联系人-市场活动关系
     * @param contactsActivityRelations
     * @return
     */
    int insertContactsActivityRelationByClueActivityRelation(@Param("contactsActivityRelations") List<ContactsActivityRelation> contactsActivityRelations);

}




