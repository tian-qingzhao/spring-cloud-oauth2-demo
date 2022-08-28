package com.tqz.oauth2.server.service.impl;

import com.tqz.oauth2.server.entity.SysUser;
import com.tqz.oauth2.server.mapper.SysUserMapper;
import com.tqz.oauth2.server.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by smlz on 2019/12/20.
 */
@Service
public class SysUserServiceImpl implements ISysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser getByUsername(String username) {
        return sysUserMapper.findByUserName(username);
    }
}
