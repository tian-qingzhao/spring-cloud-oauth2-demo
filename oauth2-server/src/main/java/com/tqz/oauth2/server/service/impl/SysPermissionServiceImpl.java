package com.tqz.oauth2.server.service.impl;

import com.tqz.oauth2.server.entity.SysPermission;
import com.tqz.oauth2.server.entity.SysRolePermission;
import com.tqz.oauth2.server.entity.SysUserRole;
import com.tqz.oauth2.server.mapper.SysPermissionMapper;
import com.tqz.oauth2.server.mapper.SysRolePermissionMapper;
import com.tqz.oauth2.server.mapper.SysUserRoleMapper;
import com.tqz.oauth2.server.service.ISysPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>权限服务层实现类
 *
 * @author tianqingzhao
 * @since 2022/8/27 15:19
 */
@Service
@Slf4j
public class SysPermissionServiceImpl implements ISysPermissionService {
    
    @Autowired
    private SysRolePermissionMapper sysRolePermissionMapper;
    
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    
    @Autowired
    private SysPermissionMapper sysPermissionMapper;
    
    @Override
    public List<SysPermission> findByUserId(Integer userId) {
        
        //根据用户ID查询出 用户角色映射列表
        List<SysUserRole> sysUserRoleList = sysUserRoleMapper.findByUserId(userId);
        if (CollectionUtils.isEmpty(sysUserRoleList)) {
            log.warn("根据用户ID:{}查询 用户角色为空", userId);
            return null;
        }
        
        //迭代循环获取roleIds
        List<Integer> roleIds = new ArrayList<>();
        for (SysUserRole sysUserRole : sysUserRoleList) {
            roleIds.add(sysUserRole.getRoleId());
        }
        
        //查询角色 资源关联集合
        List<SysRolePermission> sysRolePermissionList = sysRolePermissionMapper.findByRoleIds(roleIds);
        if (CollectionUtils.isEmpty(sysRolePermissionList)) {
            log.warn("根据RoleIds:{}查询SysRolePermission集合为空", roleIds);
            return null;
        }
        
        //迭代permissionId 加入集合
        List<Integer> permissionIds = new ArrayList<>();
        for (SysRolePermission sysRolePermission : sysRolePermissionList) {
            permissionIds.add(sysRolePermission.getPermissionId());
        }
        
        //查询用户的所有资源
        List<SysPermission> sysPermissionList = sysPermissionMapper.findByIds(permissionIds);
        if (CollectionUtils.isEmpty(sysPermissionList)) {
            log.warn("根据permissionIds:{} 查询SysPermission为空", permissionIds);
        }
        return sysPermissionList;
    }
}
