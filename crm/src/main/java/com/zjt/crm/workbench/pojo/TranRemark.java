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
 * @TableName tbl_tran_remark
 */
@TableName(value ="tbl_tran_remark")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TranRemark implements Serializable {
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
    private String tranId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}