package com.zjt.crm.settings.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.zjt.crm.commons.constant.Constant;
import com.zjt.crm.commons.pojo.ReturnObject;
import com.zjt.crm.commons.utils.DateUtils;
import com.zjt.crm.settings.pojo.User;
import com.zjt.crm.settings.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @author zhu
 * @version 1.0
 * 一个资源目录一个controller :
 * user目录下
 */
@Slf4j
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    //请求映射保持和资源路径一致
    @RequestMapping("/settings/qx/user/toLogin.do")
    public String toLogin() {
        //接收index页面的请求，跳转到登陆页面
        return "settings/qx/user/login";
    }


    //点击登录时
    @ResponseBody
    @RequestMapping("/settings/qx/user/login.do")
    public Object login(String loginAct, String loginPwd, String isRemPwd, HttpServletRequest request,
                        HttpServletResponse response) {

        //根据登录名及密码查询用户
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(StringUtils.isNotBlank(loginAct), User::getLoginAct, loginAct)
                .eq(StringUtils.isNotBlank(loginPwd), User::getLoginPwd, loginPwd);
        User user = userService.getOne(userLambdaQueryWrapper);
        //返回给前台的数据
        ReturnObject returnObject = new ReturnObject();

        //根据查询结果生成响应信息
        if (null == user) {
            //用户名或密码错误
            returnObject.setCode(Constant.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("用户名或密码错误");
        } else {
            if (DateUtils.formatDateTime(new Date()).compareTo(user.getExpireTime()) > 0) {
                //当前时间比过期时间大表示账号过期，比如合同到期时间为过期时间
                returnObject.setCode(Constant.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("账号已过期");
            } else if ("0".equals(user.getLockState())) {
                //状态被锁定，如辞职锁定账号
                returnObject.setCode(Constant.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("账号已锁定");
            } else if (!user.getAllowIps().contains(request.getRemoteAddr())) {
                //ip不被允许,登陆失败
                returnObject.setCode(Constant.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("ip受限");
            } else {
                //登陆成功
                returnObject.setCode(Constant.RETURN_OBJECT_CODE_SUCCESS);

                //将用户保存到session中
                HttpSession session = request.getSession();
                session.setAttribute(Constant.CURRENT_USER, user);

                Cookie loginActCookie = new Cookie(Constant.COOKIE_LOGIN_ACT, user.getLoginAct());
                Cookie loginPwdCookie = new Cookie(Constant.COOKIE_LOGIN_PWD, user.getLoginPwd());
                if ("true".equals(isRemPwd)) {
                    //记住密码
                    System.out.println(loginActCookie.getValue());
                    //将cookie保存在客户端硬盘上10天
                    loginActCookie.setMaxAge(60 * 60 * 24 * 10);
                    loginPwdCookie.setMaxAge(60 * 60 * 24 * 10);
                    response.addCookie(loginActCookie);
                    response.addCookie(loginPwdCookie);

                } else {

                    //不记住密码
                    /*Cookie[] cookies = request.getCookies();
                    for (Cookie cookie : cookies) {
                        //之前记住过密码
                        if (Constant.COOKIE_LOGIN_PWD.equals(cookie.getName())
                                || Constant.COOKIE_LOGIN_ACT.equals(cookie.getName())) {
                            //删除记住密码cookie以及账号密码cookie
                            cookie.setMaxAge(0);
                        }
                    }*/
                    //对啊，cookie可以直接覆盖，并且立即销毁，不用遍历
                    loginActCookie.setMaxAge(0);
                    loginPwdCookie.setMaxAge(0);
                    response.addCookie(loginActCookie);
                    response.addCookie(loginPwdCookie);
                }

            }
        }
        return returnObject;
    }

    @RequestMapping("/settings/qx/user/logout.do")
    public String logout(HttpSession session, HttpServletResponse response) {
        //用户点击退出
        //清空用户名用户密码cookie
        Cookie cookie = new Cookie(Constant.COOKIE_LOGIN_ACT, "1");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        Cookie cookie2 = new Cookie(Constant.COOKIE_LOGIN_PWD, "1");
        cookie2.setMaxAge(0);
        response.addCookie(cookie2);
        //清空session
        session.invalidate();
        //跳转到首页
        return "redirect:/";
    }

}
