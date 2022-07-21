package com.zjt.crm.settings.mapper;

import com.zjt.crm.settings.pojo.DicValue;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author ugozjt
* @description 针对表【tbl_dic_value】的数据库操作Mapper
* @createDate 2022-07-15 21:55:30
* @Entity com.zjt.crm.settings.pojo.DicValue
*/
@Repository
public interface DicValueMapper extends BaseMapper<DicValue> {

    /**
     * 根据数据字典类型查询数据字典值
     * @param dicType
     * @return
     */
    List<DicValue> selectDicValueByDicType(String dicType);

}




