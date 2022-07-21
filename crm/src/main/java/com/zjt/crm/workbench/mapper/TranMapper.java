package com.zjt.crm.workbench.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjt.crm.workbench.pojo.FunnelVO;
import com.zjt.crm.workbench.pojo.Tran;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author ugozjt
 * @description 针对表【tbl_tran】的数据库操作Mapper
 * @createDate 2022-07-17 02:38:39
 * @Entity com.zjt.crm.workbench.pojo.Tran
 */
@Repository
public interface TranMapper extends BaseMapper<Tran> {

    /**
     * 添加交易
     *
     * @param tran
     * @return
     */
    int insertTran(Tran tran);

    /**
     * 根据分页信息以及查询条件获取交易信息
     * @param page page(pageNo,pageSize)
     * @param tran 将查询条件封装到 tran实体对象中
     * @return IPage<Tran>
     */
    IPage<Tran> selectTranForPageByCondition(@Param("page") Page<Tran> page, @Param("tran") Tran tran);

    /**
     * 根据交易id查询交易详细信息用于交易明细页面 : 表连接 name代替id
     * @param tranId
     * @return
     */
    Tran selectTranForDetailById(String tranId);

    /**
     * 查询各个交易阶段的交易数量
     * @return
     */
    List<FunnelVO> getTranCountGroupByStage();

}




