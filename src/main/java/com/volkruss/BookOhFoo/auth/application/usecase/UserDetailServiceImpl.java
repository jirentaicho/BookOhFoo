package com.volkruss.BookOhFoo.auth.application.usecase;

import com.volkruss.BookOhFoo.auth.domain.model.AdminUser;
import com.volkruss.BookOhFoo.auth.domain.model.LoginUserDto;
import com.volkruss.BookOhFoo.auth.domain.model.UserRepository;
import com.volkruss.BookOhFoo.auth.domain.model.UserRoleRepositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    @Qualifier("UserRepositoryMap")
    private UserRepository adminUserRepository;

    @Autowired
    @Qualifier("UserRoleRepositoryMap")
    private UserRoleRepositroy userRoleRepositroy;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 管理者の取得を行う
        LoginUserDto loginUserDto = this.adminUserRepository.findUserByUsername(username);

        // 管理者のロールを取得を行う
        List<String> roles = this.userRoleRepositroy.findUserRoleByUsername(username);

        // GrantedAuthorityに詰め替える
        Set<GrantedAuthority> roleSet = roles.stream()
                .map( role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toSet());
        // AdminUserの生成
        AdminUser adminUser = new AdminUser(loginUserDto.getName(),loginUserDto.getPassword(),roleSet);

        return adminUser;
    }
}
