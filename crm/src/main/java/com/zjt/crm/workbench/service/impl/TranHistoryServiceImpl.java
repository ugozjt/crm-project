package com.zjt.crm.workbench.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjt.crm.workbench.pojo.TranHistory;
import com.zjt.crm.workbench.service.TranHistoryService;
import com.zjt.crm.workbench.mapper.TranHistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ugozjt
 * @description 针对表【tbl_tran_history】的数据库操作Service实现
 * @createDate 2022-07-18 01:38:54
 */
@Service
public class TranHistoryServiceImpl extends ServiceImpl<TranHistoryMapper, TranHistory>
        implements TranHistoryService {

    @Autowired
    private TranHistoryMapper tranHistoryMapper;

    @Override
    public List<TranHistory> selectTranHistoryByTranId(String tranId) {
        return tranHistoryMapper.selectTranHistoryByTranId(tranId);

    }
}




