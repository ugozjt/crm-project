package com.zjt.crm.settings.web.config;

import com.zjt.crm.settings.web.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zhu
 * @version 1.0
 */
@Configuration
public class MyConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截器注册到容器中
        registry.addInterceptor(new LoginInterceptor())
                //设置拦截路径
                .addPathPatterns("/settings/**", "/workbench/**")
                //不拦截路径 : 静态资源,登录页面
                .excludePathPatterns("/", "/image/**", "/jquery/**", "/settings/qx/user/toLogin.do", "/settings/qx/user/login.do");
    }
}
