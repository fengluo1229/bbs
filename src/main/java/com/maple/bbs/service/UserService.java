package com.maple.bbs.service;

import com.maple.bbs.domain.User;

import java.util.List;

public interface UserService {
    List<User> queryAllUser();
    int register(User user);
    User queryInfo(String userName);
    int modifyInfo(User user);
}
