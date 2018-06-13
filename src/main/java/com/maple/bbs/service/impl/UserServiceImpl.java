package com.maple.bbs.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.page.PageMethod;
import com.maple.bbs.domain.User;
import com.maple.bbs.mapper.UserMapper;
import com.maple.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> queryAllUser() {
        PageHelper.startPage(1,10);
        List users=userMapper.selectAllUser();
        return users;
    }

    @Override
    @Transactional
    public int register(User user) {
        try {
            user.setRegisterTime(new Date());
            userMapper.insertUser(user);
            return 1;
        } catch (Exception e){
            e.printStackTrace();
            return -1;//用户已存在
        }
    }

    @Override
    public User queryInfo(String userName) {
        User user = userMapper.selectUserByName(userName);
        return user;
    }

    @Override
    @Transactional
    public int modifyInfo(User user) {
        int resultNum = userMapper.updateUser(user);
        return resultNum;
    }

    @Override
    @Transactional
    public int banUser(String userName, Date date) {
        try{
            userMapper.banUser(date,userName);
            return 0;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    @Transactional
    public int cancelBan(String userName){
        try{
            userMapper.cancelBan(userName);
            return 0;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public List<User> queryAllBanUser(String page) {
        PageHelper.startPage(Integer.valueOf(page),10);
        return userMapper.selectAllBanUser();
    }

    @Override
    public int banUsersPage() {
        int num = userMapper.pageForBanUser();
        int pageNum = num/10+1;
        return pageNum;
    }

}
