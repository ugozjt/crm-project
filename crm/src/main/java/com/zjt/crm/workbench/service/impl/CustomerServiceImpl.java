package com.zjt.crm.workbench.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjt.crm.workbench.pojo.Customer;
import com.zjt.crm.workbench.service.CustomerService;
import com.zjt.crm.workbench.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ugozjt
 * @description 针对表【tbl_customer】的数据库操作Service实现
 * @createDate 2022-07-16 23:56:04
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer>
        implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public int insertCustomer(Customer customer) {
        return customerMapper.insertCustomer(customer);
    }

    @Override
    public List<String> selectAllCustomerNameByLikeName(String customerName) {

        return customerMapper.selectAllCustomerNameByLikeName(customerName);
    }
}




