package com.zjt.crm.commons.utils;

import java.util.UUID;

/**
 * @author zhu
 * @version 1.0
 * UUID去掉-的工具类
 */
public class UUIDUtils {

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
