package com.maple.bbs.mapper;

import com.maple.bbs.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface UserMapper {
    List<User> selectAllUser();
    User selectUserByName(String name);
    int insertUser(User user);
    int updateUser(User user);
    int banUser(Date date,String userName);
    int cancelBan(String userName);
    List<User> selectAllBanUser();
    int userNum(String userState);
}
