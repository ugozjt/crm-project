package com.zjt.crm.workbench.web.controller;

import com.zjt.crm.commons.constant.Constant;
import com.zjt.crm.commons.pojo.ReturnObject;
import com.zjt.crm.commons.utils.DateUtils;
import com.zjt.crm.commons.utils.UUIDUtils;
import com.zjt.crm.settings.pojo.User;
import com.zjt.crm.workbench.pojo.ActivityRemark;
import com.zjt.crm.workbench.service.ActivityRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @author zhu
 * @version 1.0
 * 虽然说一个资源目录对应一个controller,但一个controller太长也不好
 */
@Controller
public class ActivityRemarkController {

    @Autowired
    private ActivityRemarkService activityRemarkService;

    /**
     * 保存活动备注
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/workbench/activity/saveRemark.do")
    public Object saveRemark(ActivityRemark activityRemark, HttpSession session) {
        User user = (User) session.getAttribute(Constant.CURRENT_USER);
        activityRemark.setCreateBy(user.getId());
        activityRemark.setCreateTime(DateUtils.formatDateTime(new Date()));
        activityRemark.setId(UUIDUtils.getUUID());
        activityRemark.setEditFlag(Constant.REMARK_EDIT_FLAG_NO_EDITED);
        ReturnObject returnObject = new ReturnObject();
        try {
            boolean save = activityRemarkService.save(activityRemark);
            if (save) {
                returnObject.setCode(Constant.RETURN_OBJECT_CODE_SUCCESS);
                //将当前activityRemark的创建者从创建者id替换成创建者姓名
                activityRemark.setCreateBy(user.getName());
                //将新添加的activityRemark发送给前台，用于刷新备注列表
                returnObject.setReturnData(activityRemark);
            } else {
                returnObject.setCode(Constant.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统繁忙,请稍后重试");
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnObject.setCode(Constant.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统繁忙,请稍后重试");
        }
        return returnObject;
    }

    @ResponseBody
    @RequestMapping("/workbench/activity/deleteActivityRemarkById.do")
    public Object deleteActivityRemark(String activityRemarkId) {
        //删除activityRemark
        ReturnObject returnObject = new ReturnObject();
        try {
            boolean remove = activityRemarkService.removeById(activityRemarkId);
            if (remove) {
                returnObject.setCode(Constant.RETURN_OBJECT_CODE_SUCCESS);
            } else {
                returnObject.setCode(Constant.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统繁忙,请稍后重试");
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnObject.setCode(Constant.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统繁忙,请稍后重试");
        }
        return returnObject;

    }

    /**
     * 修改备注
     */
    @ResponseBody
    @RequestMapping("/workbench/activity/editRemark.do")
    public Object editRemark(ActivityRemark activityRemark, HttpSession session) {
        User user = (User) session.getAttribute(Constant.CURRENT_USER);
        //修改备注的修改人，修改时间，修改标记，修改内容
        activityRemark.setEditBy(user.getId());
        activityRemark.setEditFlag(Constant.REMARK_EDIT_FLAG_YES_EDITED);
        activityRemark.setEditTime(DateUtils.formatDateTime(new Date()));

        ReturnObject returnObject = new ReturnObject();
        try {
            boolean update = activityRemarkService.updateById(activityRemark);
            if (update) {
                returnObject.setCode(Constant.RETURN_OBJECT_CODE_SUCCESS);
                returnObject.setReturnData(activityRemark);
            } else {
                returnObject.setCode(Constant.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统繁忙,请稍后重试");
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnObject.setCode(Constant.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统繁忙,请稍后重试");
        }
        return returnObject;
    }

}
