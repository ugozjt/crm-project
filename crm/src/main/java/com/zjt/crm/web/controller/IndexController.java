package com.zjt.crm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhu
 * @version 1.0
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String index() {
        //进入系统跳转到index页面
        return "/index";
    }

}
