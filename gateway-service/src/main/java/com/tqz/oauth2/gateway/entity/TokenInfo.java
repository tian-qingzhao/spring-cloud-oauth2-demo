/**
 *
 */

package com.tqz.oauth2.gateway.entity;

import java.util.Arrays;
import java.util.Date;

/**
 * <p>封装token信息
 *
 * @author tianqingzhao
 * @since 2022/8/27 17:06
 */
public class TokenInfo {
    
    private boolean active;
    
    private String clientId;
    
    private String[] scope;
    
    private String userName;
    
    private String[] aud;
    
    private Date exp;
    
    private String[] authorities;
    
    public boolean isActive() {
        return active;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }
    
    public String getClientId() {
        return clientId;
    }
    
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
    
    public String[] getScope() {
        return scope;
    }
    
    public void setScope(String[] scope) {
        this.scope = scope;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String[] getAud() {
        return aud;
    }
    
    public void setAud(String[] aud) {
        this.aud = aud;
    }
    
    public Date getExp() {
        return exp;
    }
    
    public void setExp(Date exp) {
        this.exp = exp;
    }
    
    public String[] getAuthorities() {
        return authorities;
    }
    
    public void setAuthorities(String[] authorities) {
        this.authorities = authorities;
    }
    
    @Override
    public String toString() {
        return "TokenInfo{" + "active=" + active + ", clientId='" + clientId + '\'' + ", scope=" + Arrays.toString(
                scope) + ", userName='" + userName + '\'' + ", aud=" + Arrays.toString(aud) + ", exp=" + exp
                + ", authorities=" + Arrays.toString(authorities) + '}';
    }
}
