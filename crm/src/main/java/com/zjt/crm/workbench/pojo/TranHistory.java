package com.zjt.crm.workbench.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName tbl_tran_history
 */
@TableName(value ="tbl_tran_history")
@Data
public class TranHistory implements Serializable {
    /**
     * 
     */
    @TableId
    private String id;

    /**
     * 
     */
    private String stage;

    /**
     * 
     */
    private String money;

    /**
     * 
     */
    private String expectedDate;

    /**
     * 
     */
    private String createTime;

    /**
     * 
     */
    private String createBy;

    /**
     * 
     */
    private String tranId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}