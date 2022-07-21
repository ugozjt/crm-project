package com.zjt.crm.workbench.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjt.crm.settings.pojo.User;
import com.zjt.crm.workbench.pojo.FunnelVO;
import com.zjt.crm.workbench.pojo.Tran;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
* @author ugozjt
* @description 针对表【tbl_tran】的数据库操作Service
* @createDate 2022-07-17 02:38:39
*/
public interface TranService extends IService<Tran> {

    void saveTran(Tran tran, User user);

    IPage<Tran> selectTranForPageByCondition(@Param("page") Page<Tran> page, Tran tran);

    Tran selectTranForDetailById(String tranId);

    List<FunnelVO> getTranCountGroupByStage();

}
