package com.zjt.crm.workbench.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @author ugozjt
 * @TableName tbl_clue
 */
@TableName(value ="tbl_clue")
@Data
public class Clue implements Serializable {
    /**
     * 
     */
    @TableId
    private String id;

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
    private String owner;

    /**
     * 
     */
    private String company;

    /**
     * 
     */
    private String job;

    /**
     * 
     */
    private String email;

    /**
     * 
     */
    private String phone;

    /**
     * 
     */
    private String website;

    /**
     * 
     */
    private String mphone;

    /**
     * 
     */
    private String state;

    /**
     * 
     */
    private String source;

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