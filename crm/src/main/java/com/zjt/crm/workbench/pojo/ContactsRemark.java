package com.zjt.crm.workbench.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author ugozjt
 * @TableName tbl_contacts_remark
 */
@TableName(value ="tbl_contacts_remark")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactsRemark implements Serializable {
    /**
     * 
     */
    @TableId
    private String id;

    /**
     * 
     */
    private String noteContent;

    /**
     * 
     */
    private String createBy;

    /**
     * 
     */
    private String createTime;

    /**
     * 
     */
    private String editBy;

    /**
     * 
     */
    private String editTime;

    /**
     * 
     */
    private String editFlag;

    /**
     * 
     */
    private String contactsId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}