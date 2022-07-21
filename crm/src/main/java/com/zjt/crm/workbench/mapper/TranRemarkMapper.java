package com.zjt.crm.workbench.mapper;

import com.zjt.crm.workbench.pojo.TranRemark;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author ugozjt
* @description 针对表【tbl_tran_remark】的数据库操作Mapper
* @createDate 2022-07-17 02:54:06
* @Entity com.zjt.crm.workbench.pojo.TranRemark
*/
@Repository
public interface TranRemarkMapper extends BaseMapper<TranRemark> {

    /**
     * 批量添加交易备注通过线索备注
     * @param tranRemarks
     * @return
     */
    int insertTranRemarkByClueRemark(@Param("tranRemarks") List<TranRemark> tranRemarks);

    /**
     * 查询当前线索的所有备注信息 表连接name代替id
     * @param tranId
     * @return
     */
    List<TranRemark> selectTranMarksByTranId(String tranId);

}




