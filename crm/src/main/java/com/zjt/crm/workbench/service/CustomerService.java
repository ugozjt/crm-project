package com.zjt.crm.workbench.service;

import com.zjt.crm.workbench.pojo.Customer;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author ugozjt
 * @description 针对表【tbl_customer】的数据库操作Service
 * @createDate 2022-07-16 23:56:04
 */
public interface CustomerService extends IService<Customer> {

    /**
     * 添加顾客
     *
     * @param customer
     * @return
     */
    int insertCustomer(Customer customer);

    List<String> selectAllCustomerNameByLikeName(String customerName);

}
