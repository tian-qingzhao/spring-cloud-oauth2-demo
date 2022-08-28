package com.tqz.oauth2.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * <p>获取用户信息，基于内存的，相当于写死
 *
 * @author tianqingzhao
 * @since 2022/8/5 17:30
 * @deprecated 使用 {@link InDbMyUserDetailService} 基于数据库查询用户信息的
 */
//@Component
@Deprecated
public class InMemoryMyUserDetailService implements UserDetailsService {
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return User.builder()
                .username(username)
                .password(passwordEncoder.encode("123456"))
                .authorities("ROLE_ADMIN")
                .build();
    }
}
