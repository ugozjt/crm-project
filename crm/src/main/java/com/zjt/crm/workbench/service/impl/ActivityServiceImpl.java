package com.zjt.crm.workbench.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjt.crm.workbench.pojo.Activity;
import com.zjt.crm.workbench.service.ActivityService;
import com.zjt.crm.workbench.mapper.ActivityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
* @author ugozjt
* @description 针对表【tbl_activity】的数据库操作Service实现
* @createDate 2022-07-12 15:05:28
*/
@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity>
    implements ActivityService{

    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public IPage<Activity> selectActivityByConditionForPage(Page<Activity> page, Map<String, Object> map) {

        return activityMapper.selectActivityByConditionForPage(page, map);
    }

    @Override
    public Activity queryActivityById(String id) {

        return activityMapper.queryActivityById(id);
    }

    @Override
    public List<Activity> queryActivityByIds(String[] ids) {
        return activityMapper.queryActivityByIds(ids);
    }

    @Override
    public Activity queryActivityForDetailById(String id) {
        return activityMapper.queryActivityForDetailById(id);
    }

    @Override
    public List<Activity> selectActivitiesForClueDetailByClueId(String clueId) {
        return activityMapper.selectActivitiesForClueDetailByClueId(clueId);
    }

    @Override
    public List<Activity> selectActivitiesForBindByActName(String activityName, String clueId) {

        return activityMapper.selectActivitiesForBindByActName(activityName, clueId);
    }

    @Override
    public List<Activity> selectActivitiesForAppendingByIds(String[] ids) {

        return activityMapper.selectActivitiesForAppendingByIds(ids);
    }

    @Override
    public List<Activity> selectActivityForClueConvertByActivityName(String activityName, String clueId) {
        return activityMapper.selectActivityForClueConvertByActivityName(activityName, clueId);
    }

    @Override
    public List<Activity> selectActivitiesForSaveTranByActName(String activityName) {

        return activityMapper.selectActivitiesForSaveTranByActName(activityName);
    }


}




