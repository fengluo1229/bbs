package com.maple.bbs.controller;

import com.maple.bbs.domain.Result;
import com.maple.bbs.service.ArticleService;
import com.maple.bbs.service.ReplyService;
import com.maple.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@RestController
@CrossOrigin
public class AdminController {
    @Autowired
    UserService userService;
    @Autowired
    ArticleService articleService;
    @Autowired
    ReplyService replyService;

    @GetMapping(value = "/admin/user")
    public Result getAllBanUser(@RequestParam(name = "page",required = false)String page){
        if(page!=null){
            return Result.resultData(200,"success",userService.queryAllBanUser(page));
        }else {
            return Result.resultData(200,"success",userService.queryAllBanUser("1"));
        }
    }
    @GetMapping(value = "/admin/user/pageNum")
    public Result getBanUsersPageNum(){
        return Result.resultData(200,"success",userService.banUsersPage());
    }

    @PostMapping(value = "/admin/user/ban")
    public Result banUser(HttpServletRequest request){
        String userName = request.getParameter("userName");
        String banTime = request.getParameter("banTime");
        if(banTime.equals("1")){
            Date date = new Date();
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            calendar.add(calendar.DATE,1);
            date = calendar.getTime();
            if(userService.banUser(userName,date)==0){
                return Result.resultMessage(200,"success");
            }
        }else if(banTime.equals("7")){
            Date date = new Date();
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            calendar.add(calendar.DATE,7);
            date = calendar.getTime();
            if(userService.banUser(userName,date)==0){
                return Result.resultMessage(200,"success");
            }
        }else {
            Date date = new Date();
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            calendar.add(calendar.YEAR,99);
            date = calendar.getTime();
            if(userService.banUser(userName,date)==0){
                return Result.resultMessage(200,"success");
            }
        }
        return Result.resultMessage(500,"error,User not found");
    }


    @PostMapping(value = "/admin/user/cancel")
    public Result cancelBan(@RequestParam("userName")String userName){
        if(userService.cancelBan(userName)==0){
            return Result.resultMessage(200,"success");
        }else {
            return Result.resultMessage(500,"error");
        }
    }

    @GetMapping(value = "/admin/deleteArticle")
    public Result getAllDeleteArticle(@RequestParam(name = "page",required = false)String page){
        if(page!=null){
            return Result.resultData(200,"success",articleService.queryAllDeleteArticle(page));
        }else {
            return Result.resultData(200,"success",articleService.queryAllDeleteArticle("1"));
        }
    }

    @PostMapping(value = "/admin/article/cancelDelete")
    public Result cancelDelete(@RequestParam("articleId")String articleId){
        if(articleService.cancelDelete(articleId)==0){
            return Result.resultMessage(200,"cancel delete successfully");
        }else {
            return Result.resultMessage(500,"cancel delete error");
        }
    }
    @GetMapping(value = "/admin/topArticle")
    public Result getAllTopArticle(@RequestParam(name = "page",required = false)String page){
        if(page!=null){
            return Result.resultData(200,"success",articleService.queryAllTopArticle(page));
        }else {
            return Result.resultData(200,"success",articleService.queryAllTopArticle("1"));
        }
    }

    @PostMapping(value = "/admin/article/cancelTop")
    public Result cancelTop(@RequestParam("articleId")String articleId){
        if(articleService.cancelTop(articleId)==0){
            return Result.resultMessage(200,"cancel top successfully");
        }else {
            return Result.resultMessage(500,"cancel top error");
        }
    }
    @GetMapping(value = "/admin/starArticle")
    public Result getAllStarArticle(@RequestParam(name = "page",required = false)String page){
        if(page!=null){
            return Result.resultData(200,"success",articleService.queryAllStarArticle(page));
        }else {
            return Result.resultData(200,"success",articleService.queryAllStarArticle("1"));
        }
    }

    @PostMapping(value = "/admin/article/cancelStar")
    public Result cancelStar(@RequestParam("articleId")String articleId){
        if(articleService.cancelStar(articleId)==0){
            return Result.resultMessage(200,"cancel Star successfully");
        }else {
            return Result.resultMessage(500,"cancel Star error");
        }
    }
    @GetMapping(value = "/admin/reply")
    public Result getAllDeleteReply(@RequestParam(name = "page",required = false)String page){
        if (page!=null){
            return Result.resultData(200,"success",replyService.queryDeleteReply(page));
        }else {
            return Result.resultData(200,"success",replyService.queryDeleteReply("1"));
        }
    }

    @PostMapping(value = "/admin/reply/recover")
    public Result recoverReply(@RequestParam("replyId")String replyId){
        if(replyService.recoverReply(replyId)==0){
            return Result.resultMessage(200,"recover successfully");
        }else {
            return Result.resultMessage(500,"recover error");
        }
    }
}
