package com.zjt.crm.workbench.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjt.crm.workbench.pojo.ActivityRemark;
import com.zjt.crm.workbench.service.ActivityRemarkService;
import com.zjt.crm.workbench.mapper.ActivityRemarkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author ugozjt
* @description 针对表【tbl_activity_remark】的数据库操作Service实现
* @createDate 2022-07-14 23:31:19
*/
@Service
public class ActivityRemarkServiceImpl extends ServiceImpl<ActivityRemarkMapper, ActivityRemark>
    implements ActivityRemarkService{

    @Autowired
    private ActivityRemarkMapper activityRemarkMapper;

    @Override
    public List<ActivityRemark> selectActivityRemarkForDetailByActivityId(String activityId) {
        return activityRemarkMapper.selectActivityRemarkForDetailByActivityId(activityId);
    }
}




