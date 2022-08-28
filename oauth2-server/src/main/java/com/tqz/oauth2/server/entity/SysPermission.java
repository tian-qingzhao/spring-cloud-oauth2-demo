package com.tqz.oauth2.server.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>权限实体类
 *
 * @author tianqingzhao
 * @since 2022/8/27 15:13
 */
public class SysPermission implements Serializable {
    
    private static final long serialVersionUID = 4285835478693487481L;
    
    private Integer id;
    
    private Integer pid;
    
    private Integer type;
    
    private String name;
    
    private String code;
    
    private String uri;
    
    private Integer seq = 1;
    
    private String createUser;
    
    private Date createTime;
    
    private String updateUser;
    
    private Date updateTime;
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getPid() {
        return pid;
    }
    
    public void setPid(Integer pid) {
        this.pid = pid;
    }
    
    public Integer getType() {
        return type;
    }
    
    public void setType(Integer type) {
        this.type = type;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public String getUri() {
        return uri;
    }
    
    public void setUri(String uri) {
        this.uri = uri;
    }
    
    public Integer getSeq() {
        return seq;
    }
    
    public void setSeq(Integer seq) {
        this.seq = seq;
    }
    
    public String getCreateUser() {
        return createUser;
    }
    
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
    
    public Date getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    public String getUpdateUser() {
        return updateUser;
    }
    
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
    
    public Date getUpdateTime() {
        return updateTime;
    }
    
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
