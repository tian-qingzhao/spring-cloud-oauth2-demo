package com.tqz.oauth2.server.domin;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * <p>自定义用户类，继承spring security的 {@link User}
 *
 * @author tianqingzhao
 * @since 2022/8/27 15:21
 */
public class CustomUser extends User {
    
    private Integer departmentId;   //  举个例子，部门ID
    
    private String mobile;  //  举个例子，假设我们想增加一个字段，这里我们增加一个mobile表示手机号
    
    public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
    
    public CustomUser(String username, String password, boolean enabled, boolean accountNonExpired,
            boolean credentialsNonExpired, boolean accountNonLocked,
            Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }
    
    @Override
    public String toString() {
        return "CustomUser{" + "departmentId=" + departmentId + ", mobile='" + mobile + '\'' + "} " + super.toString();
    }
}
