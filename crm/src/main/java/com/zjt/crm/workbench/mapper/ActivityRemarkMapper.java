package com.zjt.crm.workbench.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.zjt.crm.workbench.pojo.ActivityRemark;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
* @author ugozjt
* @description 针对表【tbl_activity_remark】的数据库操作Mapper
* @createDate 2022-07-14 23:31:19
* @Entity com.zjt.crm.workbench.pojo.ActivityRemark
*/
@Repository
public interface ActivityRemarkMapper extends BaseMapper<ActivityRemark> {

    /**
     * 根据activityId查询activity备注的详细信息，例 : 表连接后使用user.name替换create_by
     * @param activityId activityId
     * @return ActivityRemark
     */
    List<ActivityRemark> selectActivityRemarkForDetailByActivityId(@Param("activityId") String activityId);

}




