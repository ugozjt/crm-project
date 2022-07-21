package com.zjt.crm.workbench.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName tbl_activity_remark
 */
@TableName(value ="tbl_activity_remark")
@Data
public class ActivityRemark implements Serializable {
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
    private String createTime;

    /**
     * 
     */
    private String createBy;

    /**
     * 
     */
    private String editTime;

    /**
     * 
     */
    private String editBy;

    /**
     * 0??ʾδ?޸ģ?1??ʾ???޸
     */
    private String editFlag;

    /**
     * 
     */
    private String activityId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}