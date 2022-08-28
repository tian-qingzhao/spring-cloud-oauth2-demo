package com.tqz.oauth2.server.mapper;

import com.tqz.oauth2.server.entity.SysUserRole;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>用户角色mapper接口
 *
 * @author tianqingzhao
 * @since 2022/8/27 15:17
 */
public interface SysUserRoleMapper {
    
    /**
     * 根据用户id查询用户角色列表
     *
     * @param userId 用户id
     * @return
     */
    @Select("select * from sys_user_role where user_id=#{userId}")
    List<SysUserRole> findByUserId(Integer userId);
}
