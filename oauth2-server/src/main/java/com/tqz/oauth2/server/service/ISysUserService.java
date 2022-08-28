package com.tqz.oauth2.server.service;

import com.tqz.oauth2.server.entity.SysUser;

/**
 * <p>用户服务层接口
 *
 * @author tianqingzhao
 * @since 2022/8/27 15:18
 */
public interface ISysUserService {
    
    /**
     * 根据用户名查询用户信息
     *
     * @param username
     * @return
     */
    SysUser getByUsername(String username);
}
