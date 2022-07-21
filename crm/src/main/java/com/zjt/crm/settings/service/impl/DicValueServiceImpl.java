package com.zjt.crm.settings.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjt.crm.settings.pojo.DicValue;
import com.zjt.crm.settings.service.DicValueService;
import com.zjt.crm.settings.mapper.DicValueMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author ugozjt
* @description 针对表【tbl_dic_value】的数据库操作Service实现
* @createDate 2022-07-15 21:55:30
*/
@Service
public class DicValueServiceImpl extends ServiceImpl<DicValueMapper, DicValue>
    implements DicValueService{

    @Autowired
    private DicValueMapper dicValueMapper;

    @Override
    public List<DicValue> selectDicValueByDicType(String dicType) {

        return dicValueMapper.selectDicValueByDicType(dicType);
    }
}




