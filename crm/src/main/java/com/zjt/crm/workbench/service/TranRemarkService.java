package com.zjt.crm.workbench.service;

import com.zjt.crm.workbench.pojo.TranRemark;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author ugozjt
* @description 针对表【tbl_tran_remark】的数据库操作Service
* @createDate 2022-07-17 02:54:06
*/
public interface TranRemarkService extends IService<TranRemark> {

    List<TranRemark> selectTranMarksByTranId(String tranId);

}
