package com.zjt.crm.workbench.web.controller;

import com.zjt.crm.workbench.service.TranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhu
 * @version 1.0
 */
@Controller
public class ChartController {

    @Autowired
    private TranService tranService;

    @RequestMapping("/workbench/chart/transaction/index.do")
    public String toTransactionChart() {

        return "workbench/chart/transaction/index";
    }

    /**
     * 查询交易表中各交易阶段的交易数量
     */
    @ResponseBody
    @RequestMapping("/workbench/chart/getTranCountGroupByStage.do")
    public Object getTranCountGroupByStage() {
        return tranService.getTranCountGroupByStage();
    }
}
