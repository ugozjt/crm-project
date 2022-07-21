package com.zjt.crm.workbench.mapper;

import com.zjt.crm.workbench.pojo.TranHistory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author ugozjt
* @description 针对表【tbl_tran_history】的数据库操作Mapper
* @createDate 2022-07-18 01:38:54
* @Entity com.zjt.crm.workbench.pojo.TranHistory
*/
@Repository
public interface TranHistoryMapper extends BaseMapper<TranHistory> {

    /**
     * 添加交易历史
     * @param tranHistory
     * @return
     */
    int saveTranHistory(TranHistory tranHistory);

    /**
     * 根据交易id查询交易历史, 表连接 name替换id
     * @param tranId
     * @return
     */
    List<TranHistory> selectTranHistoryByTranId(String tranId);

}




