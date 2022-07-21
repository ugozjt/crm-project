package com.zjt.crm.workbench.mapper;

import com.zjt.crm.workbench.pojo.Customer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author ugozjt
* @description 针对表【tbl_customer】的数据库操作Mapper
* @createDate 2022-07-16 23:56:04
* @Entity com.zjt.crm.workbench.pojo.Customer
*/
@Repository
public interface CustomerMapper extends BaseMapper<Customer> {

    /**
     * 添加顾客
     * @param customer
     * @return
     */
    int insertCustomer(Customer customer);

    /**
     * 查询所有客户名称 ： 根据用户输入的客户名称模糊查询
     * @param customerName
     * @return
     */
    List<String> selectAllCustomerNameByLikeName(String customerName);

    /**
     * 根据客户名称查询客户信息
     * @param customerName
     * @return
     */
    Customer selectCustomerByCustomerName(String customerName);

}




