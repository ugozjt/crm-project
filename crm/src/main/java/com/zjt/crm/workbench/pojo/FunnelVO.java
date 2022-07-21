package com.zjt.crm.workbench.pojo;

import lombok.Data;

/**
 * 用于保存查询到的各个交易阶段的交易数量
 * @author zhu
 * @version 1.0
 */
@Data
public class FunnelVO {
    /**
     * 交易阶段
     */
    private String name;
    /**
     * 此交易阶段的交易数量
     */
    private Integer value;

}
