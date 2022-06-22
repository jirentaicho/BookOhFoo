package com.volkruss.BookOhFoo.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class AuthorizationConfig implements WebMvcConfigurer {

    @Autowired
    private SystemInterceptor systemInterceptor;

    // ユーザーの持っている認可のチェックをします
    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry){
        interceptorRegistry.addInterceptor(systemInterceptor)
                .addPathPatterns("/system/**");
    }

}
