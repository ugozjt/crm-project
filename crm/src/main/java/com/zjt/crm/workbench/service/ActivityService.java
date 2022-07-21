package com.zjt.crm.workbench.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjt.crm.workbench.pojo.Activity;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
* @author ugozjt
* @description 针对表【tbl_activity】的数据库操作Service
* @createDate 2022-07-12 15:05:28
*/
public interface ActivityService extends IService<Activity> {

    /**
     * 根据条件查询activity并分页
     * @param page mybatis-plus分页对象
     * @param map 条件
     * @return List<Activity>
     */
    IPage<Activity> selectActivityByConditionForPage(Page<Activity> page, Map<String, Object> map);

    /**
     * 查询activity
     * @param id 通过id
     * @return Activity
     */
    Activity queryActivityById(String id);

    List<Activity> queryActivityByIds(String[] ids);

    Activity queryActivityForDetailById(String id);

    List<Activity> selectActivitiesForClueDetailByClueId(String clueId);

    List<Activity> selectActivitiesForBindByActName(@Param("activityName") String activityName, @Param("clueId") String clueId);

    List<Activity> selectActivitiesForAppendingByIds(String[] ids);

    List<Activity> selectActivityForClueConvertByActivityName(@Param("activityName") String activityName, @Param("clueId") String clueId);

    List<Activity> selectActivitiesForSaveTranByActName(String activityName);

}
