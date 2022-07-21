package com.zjt.crm.workbench.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhu
 * @version 1.0
 * workbench/main目录下
 */
@Controller
public class MainController {

    @RequestMapping("/workbench/main/index.do")
    public String index() {
        return "workbench/main/index";
    }

}
