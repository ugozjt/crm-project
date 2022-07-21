package com.zjt.crm.workbench.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjt.crm.commons.constant.Constant;
import com.zjt.crm.commons.pojo.ReturnObject;
import com.zjt.crm.settings.pojo.DicValue;
import com.zjt.crm.settings.pojo.User;
import com.zjt.crm.settings.service.DicValueService;
import com.zjt.crm.settings.service.UserService;
import com.zjt.crm.workbench.mapper.ActivityMapper;
import com.zjt.crm.workbench.pojo.Customer;
import com.zjt.crm.workbench.pojo.Tran;
import com.zjt.crm.workbench.pojo.TranHistory;
import com.zjt.crm.workbench.pojo.TranRemark;
import com.zjt.crm.workbench.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author zhu
 * @version 1.0
 */
@Controller
public class TransactionController {

    @Autowired
    private DicValueService dicValueService;
    @Autowired
    private UserService userService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private TranService tranService;
    @Autowired
    private ActivityService activityService;
    @Autowired
    private ContactsService contactsService;
    @Autowired
    private TranHistoryService tranHistoryService;
    @Autowired
    private TranRemarkService tranRemarkService;

    //跳转到交易界面
    @RequestMapping("/workbench/transaction/index.do")
    public String index(Model model) {

        //查询 来源 数据字段值
        List<DicValue> sourceList = dicValueService.selectDicValueByDicType("source");
        //查询 阶段 数据字典值
        List<DicValue> stageList = dicValueService.selectDicValueByDicType("stage");
        //查询 交易类型 数据字典值
        List<DicValue> transactionTypeList = dicValueService.selectDicValueByDicType("transactionType");
        //保存到request中
        model.addAttribute("sourceList", sourceList);
        model.addAttribute("stageList", stageList);
        model.addAttribute("transactionTypeList", transactionTypeList);
        return "workbench/transaction/index";
    }

    /**
     * 跳转到创建交易页面
     *
     * @return
     */
    @RequestMapping("/workbench/transaction/toSave.do")
    public String toSave(Model model) {
        //查询用户
        List<User> users = userService.list();
        model.addAttribute("users", users);
        //查询 来源 数据字段值
        List<DicValue> sourceList = dicValueService.selectDicValueByDicType("source");
        //查询 阶段 数据字典值
        List<DicValue> stageList = dicValueService.selectDicValueByDicType("stage");
        //查询 交易类型 数据字典值
        List<DicValue> transactionTypeList = dicValueService.selectDicValueByDicType("transactionType");
        //保存到request中
        model.addAttribute("sourceList", sourceList);
        model.addAttribute("stageList", stageList);
        model.addAttribute("transactionTypeList", transactionTypeList);
        return "workbench/transaction/save";
    }

    /**
     * 根据交易阶段获取可能性
     *
     * @param stageValue
     * @return
     */
    @ResponseBody
    @RequestMapping("/workbench/transaction/getPossibilityByStage.do")
    public Object getPossibilityByStage(String stageValue) {

        ResourceBundle possibility = ResourceBundle.getBundle("possibility");
        return possibility.getString(stageValue);
    }

    /**
     * 获取所有客户名字，用于自动补全
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/workbench/transaction/getAllCustomerName.do")
    public Object getAllCustomerName(String customerName) {
        return customerService.selectAllCustomerNameByLikeName(customerName);
    }

    /**
     * 保存交易 :
     * 1、客户不存在根据客户名称创建客户
     * 2、添加一条交易信息
     * 3、添加一条交易历史信息 (交易有阶段)
     *
     * @param tran 页面传输的交易信息
     * @return 成功与否
     */
    @ResponseBody
    @RequestMapping("/workbench/transaction/saveTransaction.do")

    public Object saveTransaction(Tran tran, HttpSession session) {
        ReturnObject returnObject = new ReturnObject();
        try {
            tranService.saveTran(tran, (User) session.getAttribute(Constant.CURRENT_USER));
            returnObject.setCode(Constant.RETURN_OBJECT_CODE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            returnObject.setCode(Constant.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统繁忙,请稍后重试");
        }
        return returnObject;
    }

    /**
     * 根据市场活动名模糊查询市场活动
     * @param activityName
     * @return
     */
    @ResponseBody
    @RequestMapping("/workbench/transaction/searchActivity.do")
    public Object searchActivity(String activityName){
        return activityService.selectActivitiesForSaveTranByActName(activityName);
    }

    /**
     * 根据联系人名称模糊查询联系人
     * @param contactsName
     * @return
     */
    @ResponseBody
    @RequestMapping("/workbench/transaction/searchContacts.do")
    public Object searchContacts(String contactsName){
        return contactsService.selectContactsByContactsName(contactsName);
    }

    /**
     * 查询交易分页信息根据条件
     * @param pageNo 当前页
     * @param pageSize 每页记录数
     * @param tran 查询条件实体类
     * @return page
     */
    @ResponseBody
    @RequestMapping("/workbench/transaction/queryTranForPageByCondition.do")
    public IPage<Tran> queryTransactionForPageByCondition(Integer pageNo, Integer pageSize, Tran tran){
        Page<Tran> tranPage = new Page<Tran>(pageNo, pageSize);
        return tranService.selectTranForPageByCondition(tranPage, tran);
    }

    @RequestMapping("/workbench/transaction/toTransactionDetail.do")
    public String toTransactionDetail(String tranId, Model model){
        //获取交易详细信息
        Tran tran = tranService.selectTranForDetailById(tranId);
        //获取此交易备注信息
        List<TranRemark> tranRemarks = tranRemarkService.selectTranMarksByTranId(tranId);
        //获取此交易历史信息
        List<TranHistory> tranHistories = tranHistoryService.selectTranHistoryByTranId(tranId);
        //获取交易阶段数据字典值
        List<DicValue> stageList = dicValueService.selectDicValueByDicType("stage");
        //获取可能性
        ResourceBundle possibility = ResourceBundle.getBundle("possibility");
        String possibilityString = possibility.getString(tran.getStage());
        tran.setPossibility(possibilityString);
        System.out.println(tran);
        model.addAttribute("tran", tran);
        model.addAttribute("tranRemarks", tranRemarks);
        model.addAttribute("tranHistories", tranHistories);
        model.addAttribute("stageList", stageList);
        return "workbench/transaction/detail";
    }

}
