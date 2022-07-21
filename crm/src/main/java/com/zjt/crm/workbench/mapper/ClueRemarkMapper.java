package com.zjt.crm.workbench.mapper;

import com.zjt.crm.workbench.pojo.ClueRemark;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author ugozjt
* @description 针对表【tbl_clue_remark】的数据库操作Mapper
* @createDate 2022-07-16 12:52:36
* @Entity com.zjt.crm.workbench.pojo.ClueRemark
*/
@Repository
public interface ClueRemarkMapper extends BaseMapper<ClueRemark> {

    /**
     * 根据线索id查询此线索的所有备注信息 ： 表连接替换id
     * @param clueId
     * @return
     */
    List<ClueRemark> selectClueRemarksByCLueId(String clueId);

    /**
     * 根据线索id查询此线索的所有备注信息 ： 不将id替换为name
     * @param clueId
     * @return
     */
    List<ClueRemark> selectClueRemarksByClueIdWithOriginalField(String clueId);

    /**
     * 删除当前线索的全部备注
     * @param clueId 线索id
     * @return 删除条数
     */
    int deleteClueRemarksByClueId(String clueId);

}




