package com.zjt.crm.workbench.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjt.crm.workbench.pojo.Clue;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author ugozjt
 * @description 针对表【tbl_clue】的数据库操作Service
 * @createDate 2022-07-15 23:42:52
 */
public interface ClueService extends IService<Clue> {

    int insertClue(Clue clue);

    Clue selectClueForDetailByClueId(String id);

    IPage<Clue> selectClueForPageByCondition(@Param("page") Page<Clue> page, Clue clue);

    /**
     * 保存线索转换
     *
     * @param map clueId, 如果要创建交易的交易信息
     * @return 成功与否
     */
    void saveClueConvert(Map<String, Object> map);

}
