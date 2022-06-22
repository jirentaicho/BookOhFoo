package com.volkruss.BookOhFoo.auth.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Collection;
import java.util.stream.Collectors;

public abstract class AbstractAuthInterceptor implements HandlerInterceptor {

    protected final boolean hasAuthority(final String name){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> grants = auth.getAuthorities();
        int result = grants.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()).indexOf(name);
        if(result == -1){
            return false;
        }
        return true;
    }
}
