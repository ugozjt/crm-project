package com.zjt.crm.workbench.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjt.crm.commons.constant.Constant;
import com.zjt.crm.commons.pojo.ReturnObject;
import com.zjt.crm.commons.utils.DateUtils;
import com.zjt.crm.commons.utils.UUIDUtils;
import com.zjt.crm.settings.pojo.DicType;
import com.zjt.crm.settings.pojo.DicValue;
import com.zjt.crm.settings.pojo.User;
import com.zjt.crm.settings.service.DicTypeService;
import com.zjt.crm.settings.service.DicValueService;
import com.zjt.crm.settings.service.UserService;
import com.zjt.crm.workbench.pojo.Activity;
import com.zjt.crm.workbench.pojo.Clue;
import com.zjt.crm.workbench.pojo.ClueActivityRelation;
import com.zjt.crm.workbench.pojo.ClueRemark;
import com.zjt.crm.workbench.service.ActivityService;
import com.zjt.crm.workbench.service.ClueActivityRelationService;
import com.zjt.crm.workbench.service.ClueRemarkService;
import com.zjt.crm.workbench.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author zhu
 * @version 1.0
 */
@Controller
public class ClueController {

    @Autowired
    private UserService userService;
    @Autowired
    private DicValueService dicValueService;
    @Autowired
    private ClueService clueService;
    @Autowired
    private ActivityService activityService;
    @Autowired
    private ClueRemarkService clueRemarkService;
    @Autowired
    private ClueActivityRelationService clueActivityRelationService;


    @RequestMapping("/workbench/clue/index.do")
    public String index(String dicCode, Model model) {
        //查询所有用户用于增删改clue
        List<User> users = userService.list();
        //保存到request中
        model.addAttribute("users", users);

        //查询称呼数据字典 (appellation)
        LambdaQueryWrapper<DicValue> dicValueLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dicValueLambdaQueryWrapper.eq(true, DicValue::getTypeCode, "appellation").orderByAsc(DicValue::getOrderNo);
        List<DicValue> appellationList = dicValueService.list(dicValueLambdaQueryWrapper);

        //查询线索来源数据字典(source)
        LambdaQueryWrapper<DicValue> dicValueLambdaQueryWrapper2 = new LambdaQueryWrapper<>();
        dicValueLambdaQueryWrapper2.eq(true, DicValue::getTypeCode, "source").orderByAsc(DicValue::getOrderNo);
        List<DicValue> sourceList = dicValueService.list(dicValueLambdaQueryWrapper2);

        //查询线索状态数据字典(clueState)
        LambdaQueryWrapper<DicValue> dicValueLambdaQueryWrapper3 = new LambdaQueryWrapper<>();
        dicValueLambdaQueryWrapper3.eq(true, DicValue::getTypeCode, "clueState").orderByAsc(DicValue::getOrderNo);
        List<DicValue> clueStateList = dicValueService.list(dicValueLambdaQueryWrapper3);

        //将数据字典存入request
        model.addAttribute("appellationList", appellationList);
        model.addAttribute("sourceList", sourceList);
        model.addAttribute("clueStateList", clueStateList);

        return "workbench/clue/index";
    }


    @ResponseBody
    @RequestMapping("/workbench/clue/saveClue.do")
    public Object saveClue(Clue clue, HttpSession session) {

        clue.setId(UUIDUtils.getUUID());
        clue.setCreateTime(DateUtils.formatDateTime(new Date()));
        User user = (User) session.getAttribute(Constant.CURRENT_USER);
        clue.setCreateBy(user.getId());

        ReturnObject returnObject = new ReturnObject();
        try {
            int i = clueService.insertClue(clue);
            if (i == 1) {
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
     * 通过clueId跳转到线索明细页面
     *
     * @param clueId clueId
     * @return clue, clueRemark, clueActivity
     */
    @RequestMapping("/workbench/clue/toClueDetail.do")
    public String toClueDetail(String clueId, Model model) {
        //查询clue
        Clue clue = clueService.selectClueForDetailByClueId(clueId);
        //查询clueRemark
        List<ClueRemark> clueRemarks = clueRemarkService.selectClueRemarksByClueId(clueId);
        //查询clue相关联的activity
        List<Activity> activities = activityService.selectActivitiesForClueDetailByClueId(clueId);
        model.addAttribute("clue", clue);
        model.addAttribute("clueRemarks", clueRemarks);
        model.addAttribute("activities", activities);
        return "workbench/clue/detail";
    }

    /**
     * 获取线索分页数据
     *
     * @param pageNo   当前页
     * @param pageSize 当前页记录数
     * @param clue     查询条件
     * @return page
     */
    @ResponseBody
    @RequestMapping("/workbench/clue/selectClueForPageByCondition.do")
    public IPage<Clue> selectClueForPageByCondition(Integer pageNo, Integer pageSize, Clue clue) {
        Page<Clue> cluePage = new Page<Clue>(pageNo, pageSize);
        return clueService.selectClueForPageByCondition(cluePage, clue);

    }

    /**
     * 获取当前clue未绑定的activity
     *
     * @param activityName 模糊搜索的市场活动名称
     * @param clueId       要绑定的clueId
     * @return List<Activity>
     */
    @RequestMapping("/workbench/clue/selectActivityForBindByClueId.do")
    @ResponseBody
    public Object selectActivityForBindByClueId(String activityName, String clueId) {
        //获取当前clue未绑定的activity
        return activityService.selectActivitiesForBindByActName(activityName, clueId);
    }

    @ResponseBody
    @RequestMapping("/workbench/clue/saveClueActivityRelationByClueAndActivityId.do")
    public Object saveClueActivityRelationByClueAndActivityId(String clueId, String[] activityId) {
        ArrayList<ClueActivityRelation> clueActivityRelations = new ArrayList<>();
        ClueActivityRelation clueActivityRelation = null;

        for (String s : activityId) {
            clueActivityRelation = new ClueActivityRelation();
            clueActivityRelation.setClueId(clueId);
            clueActivityRelation.setId(UUIDUtils.getUUID());
            clueActivityRelation.setActivityId(s);
            clueActivityRelations.add(clueActivityRelation);
        }
        //线索关联活动，关联成功
        ReturnObject returnObject = new ReturnObject();
        try {
            int i = clueActivityRelationService.saveClueActivityRelationByClueAndActivityId(clueActivityRelations);
            if (i > 0) {
                //将关联成功的活动返回
                List<Activity> activities = activityService.selectActivitiesForAppendingByIds(activityId);
                returnObject.setCode(Constant.RETURN_OBJECT_CODE_SUCCESS);
                returnObject.setReturnData(activities);
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
     * 删除线索和指定市场活动的关联
     *
     * @param activityId
     * @param clueId
     * @return
     */
    @ResponseBody
    @RequestMapping("/workbench/clue/deleteClueActivityRelation.do")
    public Object deleteClueActivityRelation(String activityId, String clueId) {
        ReturnObject returnObject = new ReturnObject();
        try {
            int delete = clueActivityRelationService.deleteByActivityIdAndClueId(activityId, clueId);
            if (delete == 1) {
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


    @RequestMapping("/workbench/clue/toConvert.do")
    public Object convertClue(String clueId, Model model) {
        //查询线索详细信息
        Clue clue = clueService.selectClueForDetailByClueId(clueId);
        //查询交易阶段数据字典
        LambdaQueryWrapper<DicValue> dicValueLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dicValueLambdaQueryWrapper.eq(true, DicValue::getTypeCode, "stage").orderByAsc(DicValue::getOrderNo);
        List<DicValue> stageList = dicValueService.list(dicValueLambdaQueryWrapper);
        model.addAttribute("stageList", stageList);
        //保存到request域中
        model.addAttribute("clue", clue);
        return "workbench/clue/convert";
    }

    /**
     * 根据市场活动名从当前线索关联的市场活动中模糊查询市场活动
     */
    @ResponseBody
    @RequestMapping("/workbench/clue/searchActivity.do")
    public Object searchActivity(String clueId, String activityName) {

        return activityService.selectActivityForClueConvertByActivityName(activityName, clueId);

    }

    /**
     * 线索转换(必须在一个事务中完成)
     * 1、把该线索中有关公司的信息转换到客户表中
     * 2、把该线索中有关个人的信息转换到联系人表中
     * 3、把该线索中所有备注信息转换到客户备注表一份
     * 4、把该线索中所有备注信息转换到联系人备注表中一份
     * 5、把该线索和市场活动的关系转换到联系人和市场活动的关系表中
     * 6、如果需要创建交易，往交易表中添加一条记录，并且将该线索中所有备注转换到交易备注表中
     * 7、删除该线索下所有的备注，删除该线索和市场活动的关联信息，删除该线索
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/workbench/clue/saveClueConvert.do")
    public Object saveClueConvert(String clueId, String money, String name, String expectedDate, String stage,
                                  String activityId, String isCreateTran, HttpSession session) {
        //封装参数
        User user = (User) session.getAttribute(Constant.CURRENT_USER);
        HashMap<String, Object> stringObjectHashMap = new HashMap<>(7);
        stringObjectHashMap.put("clueId", clueId);
        stringObjectHashMap.put("money", money);
        stringObjectHashMap.put("name", name);
        stringObjectHashMap.put("expectedDate", expectedDate);
        stringObjectHashMap.put("stage", stage);
        stringObjectHashMap.put("activityId", activityId);
        stringObjectHashMap.put("isCreateTran", isCreateTran);
        stringObjectHashMap.put("user", user);

        ReturnObject returnObject = new ReturnObject();
        try {
            //转换线索
            clueService.saveClueConvert(stringObjectHashMap);
            returnObject.setCode(Constant.RETURN_OBJECT_CODE_SUCCESS);

        } catch (Exception e) {
            e.printStackTrace();
            returnObject.setCode(Constant.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统繁忙,请稍后重试");
        }
        return returnObject;
    }

}
