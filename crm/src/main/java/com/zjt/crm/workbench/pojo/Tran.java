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
 * @TableName tbl_tran
 */
@TableName(value ="tbl_tran")
@Data
public class Tran implements Serializable {
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
    private String money;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String expectedDate;

    /**
     * 
     */
    private String customerId;

    /**
     * 
     */
    private String stage;

    /**
     * 
     */
    private String type;

    /**
     * 
     */
    private String source;

    /**
     * 
     */
    private String activityId;

    /**
     * 
     */
    private String contactsId;

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
     * 扩展属性,不在数据库中,用于保存跟交易阶段对应的可能性
     */
    private String possibility;
    /**
     * 扩展属性,不在数据库中,
     * 在交易明细页面判断当前交易阶段前、当前交易后的阶段，用于展示交易进度条
     */
    private String orderNo;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}