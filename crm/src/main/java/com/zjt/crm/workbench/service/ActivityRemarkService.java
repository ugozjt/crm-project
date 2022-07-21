package com.zjt.crm.workbench.service;

import com.zjt.crm.workbench.pojo.ActivityRemark;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author ugozjt
* @description 针对表【tbl_activity_remark】的数据库操作Service
* @createDate 2022-07-14 23:31:19
*/
public interface ActivityRemarkService extends IService<ActivityRemark> {

    /**
     * 根据activityId查询activity备注的详细信息，例 : 表连接后使用user.name替换create_by
     * @param activityId activityId
     * @return ActivityRemark
     */
    List<ActivityRemark> selectActivityRemarkForDetailByActivityId(String activityId);

}
