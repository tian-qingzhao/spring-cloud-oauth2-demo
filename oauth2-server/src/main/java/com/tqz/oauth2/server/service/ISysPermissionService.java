package com.tqz.oauth2.server.service;

import com.tqz.oauth2.server.entity.SysPermission;

import java.util.List;

/**
 * <p>权限服务层接口
 *
 * @author tianqingzhao
 * @since 2022/8/27 15:17
 */
public interface ISysPermissionService {
    
    List<SysPermission> findByUserId(Integer userId);
}
