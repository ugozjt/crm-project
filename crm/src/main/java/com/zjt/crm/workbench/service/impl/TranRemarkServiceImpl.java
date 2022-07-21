package com.zjt.crm.workbench.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjt.crm.workbench.pojo.TranRemark;
import com.zjt.crm.workbench.service.TranRemarkService;
import com.zjt.crm.workbench.mapper.TranRemarkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author ugozjt
* @description 针对表【tbl_tran_remark】的数据库操作Service实现
* @createDate 2022-07-17 02:54:06
*/
@Service
public class TranRemarkServiceImpl extends ServiceImpl<TranRemarkMapper, TranRemark>
    implements TranRemarkService{

    @Autowired
    private TranRemarkMapper tranRemarkMapper;

    @Override
    public List<TranRemark> selectTranMarksByTranId(String tranId) {
        return tranRemarkMapper.selectTranMarksByTranId(tranId);
    }
}




