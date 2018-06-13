package com.maple.bbs.service;

import com.maple.bbs.domain.User;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface UserService {
    List<User> queryAllUser();
    int register(User user);
    User queryInfo(String userName);
    int modifyInfo(User user);
    int banUser(String userName, Date date);
    int cancelBan(String userName);
    List<User> queryAllBanUser(String page);
    int banUsersPage();
}
