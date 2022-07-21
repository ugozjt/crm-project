package com.zjt.crm.settings.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @author ugozjt
 * @TableName tbl_dic_type
 */
@TableName(value ="tbl_dic_type")
@Data
public class DicType implements Serializable {
    /**
     * ????????????????Ϊ?գ????ܺ??????ġ?
     */
    @TableId
    private String code;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String description;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}