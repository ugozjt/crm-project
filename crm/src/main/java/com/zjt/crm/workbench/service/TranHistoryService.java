package com.zjt.crm.workbench.service;

import com.zjt.crm.workbench.pojo.TranHistory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author ugozjt
* @description 针对表【tbl_tran_history】的数据库操作Service
* @createDate 2022-07-18 01:38:54
*/
public interface TranHistoryService extends IService<TranHistory> {

    List<TranHistory> selectTranHistoryByTranId(String tranId);

}
