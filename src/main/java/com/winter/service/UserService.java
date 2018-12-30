package com.winter.service;

import com.winter.pojo.User;

public interface UserService {
    boolean checkAccount(String username, String password);
    User getUser(String username);
    boolean checkUsername(String username);
    boolean register(String username, String password);
}
