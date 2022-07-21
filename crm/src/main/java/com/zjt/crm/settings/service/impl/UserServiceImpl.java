package com.zjt.crm.settings.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjt.crm.settings.pojo.User;
import com.zjt.crm.settings.service.UserService;
import com.zjt.crm.settings.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author ugozjt
* @description 针对表【tbl_user】的数据库操作Service实现
* @createDate 2022-07-11 15:41:43
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




