package com.zjt.crm.commons.pojo;

import lombok.Data;

/**
 * @author zhu
 * @version 1.0
 */
@Data
public class ReturnObject {
    //成功或失败的标记 1 ： 成功 0 ： 失败
    private String code;
    //提示信息
    private String message;
    //其他数据
    private Object returnData;

}
