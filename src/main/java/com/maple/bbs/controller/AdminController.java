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
public class AdminController {
    @Autowired
    UserService userService;
    @Autowired
    ArticleService articleService;
    @Autowired
    ReplyService replyService;

    //获取所有用户数
    @GetMapping(value = "/admin/allUserNum")
    public Result getAllUserNum(){
        return Result.resultData(200,"success",userService.userNum("0")+userService.userNum("1"));
    }
    //获取所有文章数
    @GetMapping(value = "/admin/allArticleNum")
    public Result getAllArticleNum(){
        return Result.resultData(200,"success",articleService.articleNum("-1")+articleService.articleNum("0")+articleService.articleNum("1")+articleService.articleNum("2"));
    }
    //获取所有回复数
    @GetMapping(value = "/admin/allReplyNum")
    public Result getAllReplyNum(){
        return Result.resultData(200,"success",replyService.replyNum("1")+replyService.replyNum("0"));
    }

    //获取封禁用户api
    @GetMapping(value = "/admin/user")
    public Result getAllBanUser(@RequestParam(name = "page",required = false)String page){
        if(page!=null){
            return Result.resultData(200,"success",userService.queryAllBanUser(page));
        }else {
            return Result.resultData(200,"success",userService.queryAllBanUser("1"));
        }
    }
    //获取封禁用户页数api
    @GetMapping(value = "/admin/user/pageNum")
    public Result getBanUsersPageNum(){
        return Result.resultData(200,"success",userService.banUsersPage());
    }
    //封禁用户api
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

    //取消用户封禁api
    @PostMapping(value = "/admin/user/cancel")
    public Result cancelBan(@RequestParam("userName")String userName){
        if(userService.cancelBan(userName)==0){
            return Result.resultMessage(200,"success");
        }else {
            return Result.resultMessage(500,"error");
        }
    }
    //获取删除文章页数api
    @GetMapping(value = "/admin/deleteArticle/pageNum")
    public Result getDeleteArticlePageNum(){
        return Result.resultData(200,"success",articleService.articleNum("-1")/20+1);
    }

    //获取删除文章api
    @GetMapping(value = "/admin/deleteArticle")
    public Result getAllDeleteArticle(@RequestParam(name = "page",required = false)String page){
        if(page!=null){
            return Result.resultData(200,"success",articleService.queryAllDeleteArticle(page));
        }else {
            return Result.resultData(200,"success",articleService.queryAllDeleteArticle("1"));
        }
    }

    //恢复删除文章api
    @PostMapping(value = "/admin/article/cancelDelete")
    public Result cancelDelete(@RequestParam("articleId")String articleId){
        if(articleService.cancelDelete(articleId)==0){
            return Result.resultMessage(200,"cancel delete successfully");
        }else {
            return Result.resultMessage(500,"cancel delete error");
        }
    }

    //获取置顶文章api
    @GetMapping(value = "/admin/topArticle")
    public Result getAllTopArticle(@RequestParam(name = "page",required = false)String page){
        if(page!=null){
            return Result.resultData(200,"success",articleService.queryAllTopArticle(page));
        }else {
            return Result.resultData(200,"success",articleService.queryAllTopArticle("1"));
        }
    }

    //取消文章置顶api
    @PostMapping(value = "/admin/article/cancelTop")
    public Result cancelTop(@RequestParam("articleId")String articleId){
        if(articleService.cancelTop(articleId)==0){
            return Result.resultMessage(200,"cancel top successfully");
        }else {
            return Result.resultMessage(500,"cancel top error");
        }
    }

    //获取精品文章页数api
    @GetMapping(value = "/admin/starArticle/pageNum")
    public Result getStarArticlePageNum(){
        return Result.resultData(200,"success",articleService.articleNum("2")/20+1);
    }

    //获取精品文章api
    @GetMapping(value = "/admin/starArticle")
    public Result getAllStarArticle(@RequestParam(name = "page",required = false)String page){
        if(page!=null){
            return Result.resultData(200,"success",articleService.queryAllStarArticle(page));
        }else {
            return Result.resultData(200,"success",articleService.queryAllStarArticle("1"));
        }
    }

    //取消文章精品api
    @PostMapping(value = "/admin/article/cancelStar")
    public Result cancelStar(@RequestParam("articleId")String articleId){
        if(articleService.cancelStar(articleId)==0){
            return Result.resultMessage(200,"cancel Star successfully");
        }else {
            return Result.resultMessage(500,"cancel Star error");
        }
    }

    //获取删除回复页数api
    @GetMapping(value = "/admin/reply/pageNum")
    public Result getDeleteReplyPageNum(){
        return Result.resultData(200,"success",replyService.replyNum("1")/20+1);
    }
    @GetMapping(value = "/admin/reply")
    public Result getAllDeleteReply(@RequestParam(name = "page",required = false)String page){
        if (page!=null){
            return Result.resultData(200,"success",replyService.queryDeleteReply(page));
        }else {
            return Result.resultData(200,"success",replyService.queryDeleteReply("1"));
        }
    }

    //恢复删除回复api
    @PostMapping(value = "/admin/reply/recover")
    public Result recoverReply(@RequestParam("replyId")String replyId){
        if(replyService.recoverReply(replyId)==0){
            return Result.resultMessage(200,"recover successfully");
        }else {
            return Result.resultMessage(500,"recover error");
        }
    }
}
