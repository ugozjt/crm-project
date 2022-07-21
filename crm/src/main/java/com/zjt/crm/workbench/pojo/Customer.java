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
 * @TableName tbl_customer
 */
@TableName(value ="tbl_customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements Serializable {
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
    private String name;

    /**
     * 
     */
    private String website;

    /**
     * 
     */
    private String phone;

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
    private String contactSummary;

    /**
     * 
     */
    private String nextContactTime;

    /**
     * 
     */
    private String description;

    /**
     * 
     */
    private String address;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}