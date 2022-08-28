package com.tqz.oauth2.server.mapper;

import com.tqz.oauth2.server.entity.SysRolePermission;

import java.util.List;

/**
 * <p>角色权限mapper接口
 *
 * @author tianqingzhao
 * @since 2022/8/27 15:15
 */
public interface SysRolePermissionMapper {
    
    /**
     * 根据角色ID查询资源列表
     *
     * @param roleIds 角色集合
     * @return 角色权限列表
     */
    List<SysRolePermission> findByRoleIds(List<Integer> roleIds);
}
