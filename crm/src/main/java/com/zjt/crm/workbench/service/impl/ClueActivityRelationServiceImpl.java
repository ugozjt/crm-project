package com.zjt.crm.workbench.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjt.crm.workbench.pojo.ClueActivityRelation;
import com.zjt.crm.workbench.service.ClueActivityRelationService;
import com.zjt.crm.workbench.mapper.ClueActivityRelationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author ugozjt
* @description 针对表【tbl_clue_activity_relation】的数据库操作Service实现
* @createDate 2022-07-16 12:34:14
*/
@Service
public class ClueActivityRelationServiceImpl extends ServiceImpl<ClueActivityRelationMapper, ClueActivityRelation>
    implements ClueActivityRelationService{

    @Autowired
    private ClueActivityRelationMapper clueActivityRelationMapper;

    @Override
    public int saveClueActivityRelationByClueAndActivityId(List<ClueActivityRelation> clueActivityRelations) {
        return clueActivityRelationMapper.saveClueActivityRelationByClueAndActivityId(clueActivityRelations);
    }

    @Override
    public int deleteByActivityIdAndClueId(String activityId, String clueId) {
        return clueActivityRelationMapper.deleteByActivityIdAndClueId(activityId, clueId);
    }
}




