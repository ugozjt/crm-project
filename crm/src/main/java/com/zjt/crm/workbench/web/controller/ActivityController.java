package com.zjt.crm.workbench.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjt.crm.commons.constant.Constant;
import com.zjt.crm.commons.pojo.ReturnObject;
import com.zjt.crm.commons.utils.DateUtils;
import com.zjt.crm.commons.utils.UUIDUtils;
import com.zjt.crm.settings.pojo.User;
import com.zjt.crm.settings.service.UserService;
import com.zjt.crm.workbench.pojo.Activity;
import com.zjt.crm.workbench.pojo.ActivityRemark;
import com.zjt.crm.workbench.service.ActivityRemarkService;
import com.zjt.crm.workbench.service.ActivityService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.tomcat.util.buf.Utf8Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author zhu
 * @version 1.0
 */
@Slf4j
@Controller
public class ActivityController {

    @Autowired
    private ActivityService activityService;
    @Autowired
    private UserService userService;
    @Autowired
    private ActivityRemarkService activityRemarkService;
    /**
     * 进入市场活动页面
     */
    @RequestMapping("/workbench/activity/index.do")
    public String index(Model model) {

        //查询所有用户用于增删改activity
        List<User> users = userService.list();
        //保存到request中
        model.addAttribute("users", users);

        return "workbench/activity/index";
    }

    /**
     * 添加市场活动
     */
    @ResponseBody
    @RequestMapping("/workbench/activity/saveActivity.do")
    public Object saveActivity(Activity activity, HttpSession session) {
        User user = (User) session.getAttribute(Constant.CURRENT_USER);
        //活动添加创建人
        activity.setCreateBy(user.getId());
        //活动添加id
        activity.setId(UUIDUtils.getUUID());
        //活动添加创建时间
        activity.setCreateTime(DateUtils.formatDateTime(new Date()));
        ReturnObject returnObject = new ReturnObject();
        try {
            //写数据try
            boolean saveActivity = activityService.save(activity);
            if (saveActivity) {
                returnObject.setCode("1");
                returnObject.setMessage("活动添加成功");
            } else {
                returnObject.setCode("0");
                returnObject.setMessage("系统繁忙,请稍后查询...");
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnObject.setCode("0");
            returnObject.setMessage("系统繁忙,请稍后查询...");
        }

        return returnObject;
    }

    /**
     * activity分页数据
     *
     * @param pageNo   当前页
     * @param pageSize 当前页记录条数
     *                 查询activity条件
     * @return Page
     */
    @ResponseBody
    @RequestMapping("/workbench/activity/page.do")
    public IPage<Activity> page(Integer pageNo, Integer pageSize, String name, String owner, String startDate,
                                String endDate) {

        Map<String, Object> condition = new HashMap<>(4);
        condition.put("name", name);
        condition.put("owner", owner);
        condition.put("startDate", startDate);
        condition.put("endDate", endDate);
        Page<Activity> activityPage = new Page<>(pageNo, pageSize);
        return activityService.selectActivityByConditionForPage(activityPage, condition);
    }

    /**
     * 批量删除activity
     * 使用数组接收数据，需要给ajax请求设置 traditional:true 属性
     *
     * @param activityIds 要删除的activity的id数组
     * @return
     */
    @ResponseBody
    @RequestMapping("/workbench/activity/deleteActivitiesById.do")
    public Object deleteActivitiesById(String[] activityIds) {

        boolean delete = activityService.removeByIds(Arrays.asList(activityIds));
        ReturnObject returnObject = new ReturnObject();
        if (delete) {
            returnObject.setCode("1");
        } else {
            returnObject.setCode("0");
        }
        return returnObject;
    }

    /**
     * 修改activity
     *
     * @param activity 根据id
     * @return 修改成功与否相关信息
     */
    @ResponseBody
    @RequestMapping("/workbench/activity/updateActivityById.do")
    public Object updateActivityById(Activity activity, HttpSession session) {
        activity.setEditTime(DateUtils.formatDateTime(new Date()));
        User user = (User) session.getAttribute(Constant.CURRENT_USER);
        activity.setEditBy(user.getId());
        ReturnObject returnObject = new ReturnObject();
        try {
            //写数据一定要try catch
            boolean update = activityService.updateById(activity);
            if (update) {
                returnObject.setCode("1");
            } else {
                returnObject.setCode("0");
                returnObject.setMessage("系统繁忙,请稍后再试");
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnObject.setCode("0");
            returnObject.setMessage("系统繁忙,请稍后再试");
        }


        return returnObject;
    }

    /**
     * 查询activity
     *
     * @param id 根据id
     * @return activity
     */
    @ResponseBody
    @RequestMapping("/workbench/activity/queryActivityById.do")
    public Object queryActivityById(String id) {
        //此查询要自己写 表连接
        return activityService.queryActivityById(id);
    }

    @RequestMapping("/workbench/activity/exportActivityByIds.do")
    public /*ResponseEntity<byte
    []>*/ void exportActivityByIds(HttpServletResponse response, String[] ids) throws IOException {
        List<Activity> activities = activityService.queryActivityByIds(ids);

        //创建excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet activitySheet = workbook.createSheet("市场活动");
        //第一行为字段名
        HSSFRow row0 = activitySheet.createRow(0);

        HSSFCell cell = row0.createCell(0);
        cell.setCellValue("id");
        cell = row0.createCell(1);
        cell.setCellValue("owner");
        cell = row0.createCell(2);
        cell.setCellValue("name");
        cell = row0.createCell(3);
        cell.setCellValue("startDate");
        cell = row0.createCell(4);
        cell.setCellValue("endDate");
        cell = row0.createCell(5);
        cell.setCellValue("cost");
        cell = row0.createCell(6);
        cell.setCellValue("description");
        cell = row0.createCell(7);
        cell.setCellValue("createTime");
        cell = row0.createCell(8);
        cell.setCellValue("createBy");
        cell = row0.createCell(9);
        cell.setCellValue("editTime");
        cell = row0.createCell(10);
        cell.setCellValue("editBy");
        Activity activity = null;
        for (int i = 1; i < activities.size() + 1; i++) {
            activity = activities.get(i - 1);
            HSSFRow row = activitySheet.createRow(i);

            HSSFCell cell1 = row.createCell(0);
            cell1.setCellValue(activity.getId());
            cell1 = row.createCell(1);
            cell1.setCellValue(activity.getOwner());
            cell1 = row.createCell(2);
            cell1.setCellValue(activity.getName());
            cell1 = row.createCell(3);
            cell1.setCellValue(activity.getStartDate());
            cell1 = row.createCell(4);
            cell1.setCellValue(activity.getEndDate());
            cell1 = row.createCell(5);
            cell1.setCellValue(activity.getCost());
            cell1 = row.createCell(6);
            cell1.setCellValue(activity.getDescription());
            cell1 = row.createCell(7);
            cell1.setCellValue(activity.getCreateTime());
            cell1 = row.createCell(8);
            cell1.setCellValue(activity.getCreateBy());
            cell1 = row.createCell(9);
            cell1.setCellValue(activity.getEditTime());
            cell1 = row.createCell(10);
            cell1.setCellValue(activity.getEditBy());
        }

        //使用responseEntity返回excel文件，需要先读取数据保存到服务器，在从服务器读取发送给客户端
        /*FileOutputStream fileOutputStream = new FileOutputStream("D:/project/testDownloadExcel/市场活动.xls");
        workbook.write(fileOutputStream);
        workbook.close();
        fileOutputStream.close();

        //把生成的excel文件下载到客户端
        byte[] bytes = null;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream("D:/project/testDownloadExcel/市场活动.xls");
            bytes = new byte[fileInputStream.available()];
            fileInputStream.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String fileName = new String("市场活动.xls".getBytes(StandardCharsets.UTF_8), "iso-8859-1");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition", "attachment;filename=" + fileName);
        //xls需设置为二进制流的形式
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<>(bytes, httpHeaders, HttpStatus.OK);*/


        String fileName = new String("市场活动.xls".getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        //直接使用workbook.write(response.getOutputStream()) 返回文件
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.flush();
    }

    /**
     * 通过excel导入activity
     * @param activityFile excel文件
     * @param session session
     * @return 导入成功与否
     * @throws IOException 抛出
     */
    @ResponseBody
    @RequestMapping("/workbench/activity/importActivity.do")
    public Object importActivity(MultipartFile activityFile, HttpSession session) throws IOException {
        //以流的形式操作文件
        InputStream inputStream = activityFile.getInputStream();
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        //读取activity excel文件保存到数据库
        //获取excel的第一页
        HSSFSheet activitySheet = workbook.getSheetAt(0);
        //最后一行的下标 : getLastRowNum
        //获取每一行
        ArrayList<Activity> activities = new ArrayList<>();
        Activity activity = null;
        User user = (User) session.getAttribute(Constant.CURRENT_USER);
        for (int i = 1; i <= activitySheet.getLastRowNum(); i++) {
            activity = new Activity();
            //id必须自动生成
            activity.setId(UUIDUtils.getUUID());
            //所有者 设置为谁导入就是谁
            activity.setOwner(user.getId());

            //获取每一列填入activity中
            HSSFRow row = activitySheet.getRow(i);
            activity.setName(row.getCell(0).getStringCellValue());
            activity.setStartDate(row.getCell(1).getStringCellValue());
            activity.setEndDate(row.getCell(2).getStringCellValue());
            activity.setCost(row.getCell(3).getStringCellValue());
            activity.setDescription(row.getCell(4).getStringCellValue());
            activity.setCreateTime(DateUtils.formatDateTime(new Date()));
            activity.setCreateBy(user.getId());
            activities.add(activity);
        }
        ReturnObject returnObject = new ReturnObject();
        try {
            boolean b = activityService.saveBatch(activities);
            if (b) {
                returnObject.setCode("1");
            } else {
                returnObject.setCode("0");
                returnObject.setMessage("系统繁忙,请稍后再试");
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnObject.setCode("0");
            returnObject.setMessage("系统繁忙,请稍后再试");
        }

        return returnObject;
    }


    /**
     * 点击对应activity去activity详细界面
     * @param id activityId
     * @return  detail界面
     */
    @RequestMapping("/workbench/activity/toActivityDetail.do")
    public String toActivityDetail(String id, Model model) {
        //查询activity通过id
        Activity activity = activityService.queryActivityForDetailById(id);
        model.addAttribute("activity", activity);
        //再通过activityId查询activity的评论表信息
        List<ActivityRemark> activityRemarks = activityRemarkService.selectActivityRemarkForDetailByActivityId(id);
        //保存到request域中
        model.addAttribute("activityRemarks", activityRemarks);
        return "workbench/activity/detail";
    }

}
