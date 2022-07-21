package com.zjt.crm.workbench.service;

import com.zjt.crm.workbench.pojo.ClueRemark;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author ugozjt
* @description 针对表【tbl_clue_remark】的数据库操作Service
* @createDate 2022-07-16 12:52:36
*/
public interface ClueRemarkService extends IService<ClueRemark> {

    List<ClueRemark> selectClueRemarksByClueId(String clueId);

}
