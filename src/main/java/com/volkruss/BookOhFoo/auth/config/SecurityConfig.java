package com.volkruss.BookOhFoo.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        // 静的コンテンツは全て許可
        return (web) -> web.ignoring().antMatchers("/webjars/**","/css/**","/img/**","/js/**");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // デフォルトのformのまま利用します
        http.formLogin(form->{
            form.successForwardUrl("/")
            .defaultSuccessUrl("/");
        });
        http.authorizeHttpRequests(authorize -> {
            authorize.antMatchers("/sell/*","/").permitAll()
                    .anyRequest().authenticated();
        });
        return http.build();
    }
}
