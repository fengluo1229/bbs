package com.maple.bbs.service.impl;

import com.github.pagehelper.PageHelper;
import com.maple.bbs.domain.User;
import com.maple.bbs.mapper.UserMapper;
import com.maple.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> queryAllUser() {
        PageHelper.startPage(1,10);
        List users = new ArrayList<User>();
        users=userMapper.selectAllUser();
        return users;
    }

    @Override
    @Transactional
    public int register(User user) {
        try {
            user.setRegisterTime(new Date());
            int resultNum = userMapper.insertUser(user);
            return 1;
        } catch (Exception e){
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
}
