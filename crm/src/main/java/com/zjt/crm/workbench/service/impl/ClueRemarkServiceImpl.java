package com.zjt.crm.workbench.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjt.crm.workbench.pojo.ClueRemark;
import com.zjt.crm.workbench.service.ClueRemarkService;
import com.zjt.crm.workbench.mapper.ClueRemarkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author ugozjt
* @description 针对表【tbl_clue_remark】的数据库操作Service实现
* @createDate 2022-07-16 12:52:36
*/
@Service
public class ClueRemarkServiceImpl extends ServiceImpl<ClueRemarkMapper, ClueRemark>
    implements ClueRemarkService{

    @Autowired
    private ClueRemarkMapper clueRemarkMapper;

    @Override
    public List<ClueRemark> selectClueRemarksByClueId(String clueId) {

        return clueRemarkMapper.selectClueRemarksByCLueId(clueId);
    }
}




