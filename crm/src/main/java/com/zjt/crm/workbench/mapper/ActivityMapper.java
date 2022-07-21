package com.zjt.crm.workbench.mapper;
import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjt.crm.workbench.pojo.Activity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
* @author ugozjt
* @description 针对表【tbl_activity】的数据库操作Mapper
* @createDate 2022-07-12 15:05:28
* @Entity com.zjt.crm.workbench.pojo.Activity
*/
@Repository
public interface ActivityMapper extends BaseMapper<Activity> {

    IPage<Activity> selectActivityByConditionForPage(@Param("page")Page<Activity> page, @Param("map") Map<String, Object> map);

    /**
     * 根据id查询市场互动详细信息 : 活动所有者为user.id 用于修改市场活动
     * @param id
     * @return
     */
    Activity queryActivityById(@Param("id") String id);

    /**
     * 根据市场活动id数组查询市场活动详细信息用于导出市场活动excel
     * @param ids
     * @return
     */
    List<Activity> queryActivityByIds(@Param("ids") String[] ids);

    /**
     * 根据活动id查询活动详细信息用于活动详细界面 : 活动所有者为user.name
     * @param id
     * @return
     */
    Activity queryActivityForDetailById(@Param("id") String id);

    /**
     * 查询关联当前线索的所有市场活动
     * @param clueId 线索id
     * @return List<Activity>
     */
    List<Activity> selectActivitiesForClueDetailByClueId(String clueId);

    /**
     * 根据activityName模糊查询市场活动，并且排除已经跟clueId关联过的市场活动
     * @param activityName
     * @param clueId
     * @return
     */
    List<Activity> selectActivitiesForBindByActName(@Param("activityName") String activityName, @Param("clueId") String clueId);

    /**
     * 根据activityName模糊查询市场活动
     * @param activityName
     * @return
     */
    List<Activity> selectActivitiesForSaveTranByActName(String activityName);

    /**
     * 根据id查询新绑定线索的市场活动，这些市场活动用于追加到已绑定市场活动列表后
     * @param ids
     * @return
     */
    List<Activity> selectActivitiesForAppendingByIds(String[] ids);

    /**
     * 根据市场活动名从当前线索关联的市场活动中模糊查询市场活动
     * @param activityName 市场活动名
     * @param clueId 线索id
     * @return List<Activity>
     */
    List<Activity> selectActivityForClueConvertByActivityName(@Param("activityName") String activityName, @Param("clueId") String clueId);
}




