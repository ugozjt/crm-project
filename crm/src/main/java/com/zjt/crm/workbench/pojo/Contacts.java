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
 * @TableName tbl_contacts
 */
@TableName(value ="tbl_contacts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contacts implements Serializable {
    /**
     * 
     */
    @TableId
    private String id;

    /**
     * 
     */
    private String owner;

    /**
     * 
     */
    private String source;

    /**
     * 
     */
    private String customerId;

    /**
     * 
     */
    private String fullname;

    /**
     * 
     */
    private String appellation;

    /**
     * 
     */
    private String email;

    /**
     * 
     */
    private String mphone;

    /**
     * 
     */
    private String job;

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
    private String description;

    /**
     * 
     */
    private String contactSummary;

    /**
     * 
     */
    private String nextContactTime;

    /**
     * 
     */
    private String address;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}