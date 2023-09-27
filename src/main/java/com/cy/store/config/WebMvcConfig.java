package com.cy.store.config;

import com.cy.store.interceptor.AuthorizationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: Zhuang Xin
 * @CreateTime: 2023-08-15  10:49
 * @Description: 设置过滤路径
 */

public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private AuthorizationInterceptor authorizationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor).addPathPatterns("/**")
                .excludePathPatterns("users/login");
    }


}
