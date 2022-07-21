package com.zjt.crm.settings.service;

import com.zjt.crm.settings.pojo.DicValue;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author ugozjt
* @description 针对表【tbl_dic_value】的数据库操作Service
* @createDate 2022-07-15 21:55:30
*/
public interface DicValueService extends IService<DicValue> {

    List<DicValue> selectDicValueByDicType(String dicType);

}
