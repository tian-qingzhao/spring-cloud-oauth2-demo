package com.tqz.oauth2.server.config;

import com.tqz.oauth2.server.domin.CustomUser;
import com.tqz.oauth2.server.entity.SysPermission;
import com.tqz.oauth2.server.entity.SysUser;
import com.tqz.oauth2.server.mapper.SysUserMapper;
import com.tqz.oauth2.server.service.ISysPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>基于数据库查询用户信息
 *
 * @author tianqingzhao
 * @since 2022/8/27 15:24
 */
@Component
@Slf4j
public class InDbMyUserDetailService implements UserDetailsService {
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private SysUserMapper sysUserMapper;
    
    @Autowired
    private ISysPermissionService sysPermissionService;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserMapper.findByUserName(username);
        
        if (null == sysUser) {
            log.warn("根据用户名:{}查询用户信息为空", username);
            throw new UsernameNotFoundException(username);
        }
        
        List<SysPermission> sysPermissionList = sysPermissionService.findByUserId(sysUser.getId());
        
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(sysPermissionList)) {
            for (SysPermission sysPermission : sysPermissionList) {
                authorityList.add(new SimpleGrantedAuthority(sysPermission.getUri()));
            }
        }
        
        CustomUser tulingUser = new CustomUser(sysUser.getUsername(), passwordEncoder.encode(sysUser.getPassword()),
                authorityList);
        log.info("用户登陆成功:{}", tulingUser);
        return tulingUser;
    }
}
