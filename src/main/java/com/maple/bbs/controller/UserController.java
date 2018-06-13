package com.maple.bbs.controller;

import com.maple.bbs.domain.Result;
import com.maple.bbs.domain.User;
import com.maple.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.util.*;
import java.util.List;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

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

    @PostMapping(value = "/login")
    public Result loginController(@RequestParam("userName")String userName, @RequestParam("password")String password, HttpServletRequest request){
        try {
            User user = userService.queryInfo(userName);
            if (user.getPassword().equals(password)) {
                request.getSession().setAttribute("user", user);
                return Result.resultMessage(200, "login successfully");
            } else {
                return Result.resultMessage(500, "Password error");
            }
        }catch (Exception e){
            return Result.resultMessage(500, "User not exist");
        }
    }

    @PostMapping(value = "/u/modify/password")
    public Result modifyPasswordController(HttpServletRequest request,
                                           @RequestParam("oldPassword")String oldPassword,
                                           @RequestParam("newPassword")String newPassword){
        User user=(User)request.getSession().getAttribute("user");
        if(user.getPassword().equals(oldPassword)){
            user.setPassword(newPassword);
            int resultNum = userService.modifyInfo(user);
            return Result.resultMessage(200,"Modify password successfully");
        }else {
            return Result.resultMessage(500,"old password error");
        }
    }

    @PostMapping(value = "/u/modify/message")
    public Result modifyInfoController(HttpServletRequest request,
                                       @RequestParam(name = "sex",required = false)String sex,
                                       @RequestParam(name = "message",required = false)String message){
        User user= (User) request.getSession().getAttribute("user");
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
}
