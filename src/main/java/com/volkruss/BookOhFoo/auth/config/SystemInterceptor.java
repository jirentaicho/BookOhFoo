package com.volkruss.BookOhFoo.auth.config;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.AccessDeniedException;

@Component
public class SystemInterceptor extends AbstractAuthInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean hasAuth = this.hasAuthority("システム管理者");
        if(!hasAuth){
            throw new AccessDeniedException("権限がありません");
        }
        return true;
    }
}
