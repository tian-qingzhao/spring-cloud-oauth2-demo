package com.tqz.oauth2.server.mapper;

import com.tqz.oauth2.server.entity.SysUser;

/**
 * <p>用户mapper接口
 *
 * @author tianqingzhao
 * @since 2022/8/27 15:16
 */
public interface SysUserMapper {
    
    /**
     * 根据用户名称查找用户信息
     *
     * @param userName 用户名
     * @return
     */
    SysUser findByUserName(String userName);
}
