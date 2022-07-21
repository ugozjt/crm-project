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
 * @TableName tbl_dic_value
 */
@TableName(value ="tbl_dic_value")
@Data
public class DicValue implements Serializable {
    /**
     * ??????????UUID
     */
    @TableId
    private String id;

    /**
     * ????Ϊ?գ?????Ҫ??ͬһ???ֵ????????ֵ?ֵ?????ظ???????Ψһ?ԡ?
     */
    private String value;

    /**
     * ????Ϊ?
     */
    private String text;

    /**
     * ????Ϊ?գ?????Ϊ?յ?ʱ????Ҫ??????????????
     */
    private String orderNo;

    /**
     * ????
     */
    private String typeCode;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}