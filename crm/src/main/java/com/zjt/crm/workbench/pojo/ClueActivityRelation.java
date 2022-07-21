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
 * @TableName tbl_clue_activity_relation
 */
@TableName(value ="tbl_clue_activity_relation")
@Data
public class ClueActivityRelation implements Serializable {
    /**
     * 
     */
    @TableId
    private String id;

    /**
     * 
     */
    private String clueId;

    /**
     * 
     */
    private String activityId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}