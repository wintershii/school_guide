package com.winter.service.impl;

import com.winter.dao.UserMapper;
import com.winter.pojo.User;
import com.winter.service.UserService;
import com.winter.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public boolean checkAccount(String username, String password) {
        String md5Password = MD5Util.MD5EncodeUtf8(password);
        return userMapper.selectValidAccount(username,md5Password) > 0;
    }

    public User getUser(String username) {
        return userMapper.selectUserByUsername(username);
    }


    public boolean checkUsername(String username) {
        return userMapper.selectNumByUsername(username) > 0;
    }

    public boolean register(String username, String password) {
        String md5Password = MD5Util.MD5EncodeUtf8(password);
        int resultCount = userMapper.insertRegister(username,md5Password);
        if (resultCount > 0) {
            return true;
        }
        return false;
    }
}
