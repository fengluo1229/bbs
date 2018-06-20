package com.maple.bbs.controller;

import com.maple.bbs.domain.Result;
import com.maple.bbs.domain.User;
import com.maple.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.util.Date;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    //注册api
    @PostMapping(value = "/register")
    public Result registerController(HttpServletRequest request){
        User user = new User();
        user.setUserName(request.getParameter("userName"));
        user.setPassword(request.getParameter("password"));
        user.setSex(Integer.valueOf(request.getParameter("sex")));
        user.setMessage(request.getParameter("message"));
        user.setRegisterTime(new Date());
        System.out.println(user.getUserName());
        System.out.println(user.getSex());
        int resultNum = userService.register(user);
        if(resultNum==1){
            return Result.resultMessage(200,"register successfully");
        }
        else {
            return Result.resultMessage(500,"User existed");
        }
    }

    //登录api
    @PostMapping(value = "/login")
    public Result loginController(@RequestParam("userName")String userName, @RequestParam("password")String password, HttpServletRequest request){
        try {
            User user = userService.queryInfo(userName);
            if (user.getPassword().equals(password)) {
                if(user.getBanTime().before(new Date())){
                    userService.cancelBan(userName);
                    user.setUserState(0);
                }
                return Result.resultData(200, "login successfully",user);
            } else {
                return Result.resultMessage(500, "Password error");
            }
        }catch (Exception e){
            return Result.resultMessage(500, "User not exist");
        }
    }

    //修改密码api
    @PostMapping(value = "/u/modify/password")
    public Result modifyPasswordController(@RequestParam("userName")String userName,
                                           @RequestParam("oldPassword")String oldPassword,
                                           @RequestParam("newPassword")String newPassword){
        User user=userService.queryInfo(userName);
        if(user.getPassword().equals(oldPassword)){
            user.setPassword(newPassword);
            int resultNum = userService.modifyInfo(user);
            return Result.resultMessage(200,"Modify password successfully");
        }else {
            return Result.resultMessage(500,"old password error");
        }
    }
    //获取用户信息
    @GetMapping(value = "/u")
    public Result getUserInfo(@RequestParam("userName")String userName){
        return Result.resultData(200,"success",userService.queryInfo(userName));
    }

    //修改个人信息api
    @PostMapping(value = "/u/modify/message")
    public Result modifyInfoController(@RequestParam("userName")String userName,
                                       @RequestParam(name = "sex",required = false)String sex,
                                       @RequestParam(name = "message",required = false)String message){
        User user=userService.queryInfo(userName);
        if(sex!=null){
            if(message!=null){
                user.setSex(Integer.valueOf(sex));
                user.setMessage(message);
            }else {
                user.setSex(Integer.valueOf(sex));
            }
        }
        int resultNum = userService.modifyInfo(user);
        if(resultNum==1) {
            return Result.resultMessage(200,"Info modify successfully");
        }else {
            return Result.resultMessage(400,"Info modify error");
        }
    }

    //获取用户头像头像
    @GetMapping(value = "/headImg")
    public Result getHeadImg(@RequestParam("userName")String userName){
        String headImg = "http://127.0.0.1:8080/img/headImg/"+userService.queryInfo(userName).getHeadImg();
        return Result.resultData(200,"success",headImg);
    }
}
