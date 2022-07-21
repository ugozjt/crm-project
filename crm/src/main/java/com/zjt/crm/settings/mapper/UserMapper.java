package com.zjt.crm.settings.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.zjt.crm.settings.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
* @author ugozjt
* @description 针对表【tbl_user】的数据库操作Mapper
* @createDate 2022-07-11 15:41:43
* @Entity com.zjt.crm.settings.pojo.User
*/
@Repository
public interface UserMapper extends BaseMapper<User> {

    User queryByLoginActAndLoginPwd(@Param("loginAct") String loginAct, @Param("loginPwd") String loginPwd);
}




