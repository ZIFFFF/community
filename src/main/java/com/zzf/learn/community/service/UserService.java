package com.zzf.learn.community.service;

import com.zzf.learn.community.mapper.UsersMapper;
import com.zzf.learn.community.model.Users;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    private UsersMapper userMapper;

    public void createOrUpdate(Users user) {
        Users dbuser = userMapper.findByAccountId(user.getAccountId());
        if (dbuser != null){
            dbuser.setToken(user.getToken());
            dbuser.setCreateTime(System.currentTimeMillis());
            dbuser.setAvatarUrl(user.getAvatarUrl());
            dbuser.setName(user.getName());
            userMapper.updateUser(dbuser);
        }else{
            userMapper.insert(user);
        }
    }
}
