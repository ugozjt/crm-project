package com.zjt.crm.workbench.service;

import com.zjt.crm.workbench.pojo.ClueActivityRelation;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author ugozjt
* @description 针对表【tbl_clue_activity_relation】的数据库操作Service
* @createDate 2022-07-16 12:34:14
*/
public interface ClueActivityRelationService extends IService<ClueActivityRelation> {

    int saveClueActivityRelationByClueAndActivityId(List<ClueActivityRelation> clueActivityRelations);

    int deleteByActivityIdAndClueId(@Param("activityId") String activityId, @Param("clueId") String clueId);

}
