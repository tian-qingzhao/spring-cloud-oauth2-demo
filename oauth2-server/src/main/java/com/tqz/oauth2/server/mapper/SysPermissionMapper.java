package com.tqz.oauth2.server.mapper;

import com.tqz.oauth2.server.entity.SysPermission;

import java.util.List;

/**
 * <p>权限mapper接口
 *
 * @author tianqingzhao
 * @since 2022/8/27 15:16
 */
public interface SysPermissionMapper {
    
    /**
     * 根据资源id集合查询资源列表
     *
     * @param ids 权限id集合
     * @return
     */
    List<SysPermission> findByIds(List<Integer> ids);
    
}
