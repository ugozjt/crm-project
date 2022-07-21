package com.zjt.crm.workbench.mapper;
import org.apache.ibatis.annotations.Param;

import com.zjt.crm.workbench.pojo.ClueActivityRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author ugozjt
* @description 针对表【tbl_clue_activity_relation】的数据库操作Mapper
* @createDate 2022-07-16 12:34:14
* @Entity com.zjt.crm.workbench.pojo.ClueActivityRelation
*/
@Repository
public interface ClueActivityRelationMapper extends BaseMapper<ClueActivityRelation> {

    /**
     * 根据线索id以及市场活动id实现线索绑定市场活动
     * @param clueActivityRelations ClueActivityRelation
     * @return 绑定成功的记录数
     */
    int saveClueActivityRelationByClueAndActivityId(List<ClueActivityRelation> clueActivityRelations);

    /**
     * 删除当前线索指定关联的市场活动 根据线索id和市场活动删除
     * @param activityId
     * @param clueId
     * @return
     */
    int deleteByActivityIdAndClueId(@Param("activityId") String activityId, @Param("clueId") String clueId);

    /**
     * 删除当前线索的所有关联的市场互动
     * @param clueId
     * @return
     */
    int deleteClueActivityRelationsByClueId(String clueId);

}




