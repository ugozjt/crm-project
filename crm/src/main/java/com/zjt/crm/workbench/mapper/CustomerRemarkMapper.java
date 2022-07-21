package com.zjt.crm.workbench.mapper;

import com.zjt.crm.workbench.pojo.CustomerRemark;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author ugozjt
* @description 针对表【tbl_customer_remark】的数据库操作Mapper
* @createDate 2022-07-17 01:16:14
* @Entity com.zjt.crm.workbench.pojo.CustomerRemark
*/
@Repository
public interface CustomerRemarkMapper extends BaseMapper<CustomerRemark> {

    /**
     * 将线索中的备注信息转换到客户备注信息表中
     * @param customerRemarks
     * @return
     */
    int insertRemarksByClueRemarks(@Param("customerRemarks") List<CustomerRemark> customerRemarks);
}




