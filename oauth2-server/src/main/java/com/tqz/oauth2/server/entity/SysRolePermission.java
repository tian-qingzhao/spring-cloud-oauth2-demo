package com.tqz.oauth2.server.entity;

import java.io.Serializable;

/**
 * <p>角色权限实体类
 *
 * @author tianqingzhao
 * @since 2022/8/27 15:13
 */
public class SysRolePermission implements Serializable {
    
    private static final long serialVersionUID = 7402412601579098788L;
    
    private Integer id;
    
    private Integer roleId;
    
    private Integer permissionId;
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getRoleId() {
        return roleId;
    }
    
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
    
    public Integer getPermissionId() {
        return permissionId;
    }
    
    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }
}
