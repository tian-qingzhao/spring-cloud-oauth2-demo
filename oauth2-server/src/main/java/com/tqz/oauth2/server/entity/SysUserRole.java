package com.tqz.oauth2.server.entity;

import java.io.Serializable;

/**
 * <p>用户角色实体类
 *
 * @author tianqingzhao
 * @since 2022/8/27 15:14
 */
public class SysUserRole implements Serializable {
    
    private static final long serialVersionUID = -1810195806444298544L;
    
    private Integer id;
    
    private Integer userId;
    
    private Integer roleId;
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getUserId() {
        return userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    public Integer getRoleId() {
        return roleId;
    }
    
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
