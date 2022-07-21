package com.zjt.crm.commons.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhu
 * @version 1.0
 * 处理日期类型的工具类
 */
public class DateUtils {

    /**
     * 对指定日期进行格式化
     * @param date 日期
     * @return String : yyyy-MM-dd HH:mm:ss
     */
    public static String formatDateTime(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }


}
