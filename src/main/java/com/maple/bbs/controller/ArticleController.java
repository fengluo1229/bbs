package com.maple.bbs.controller;

import com.maple.bbs.domain.Article;
import com.maple.bbs.domain.Reply;
import com.maple.bbs.domain.Result;
import com.maple.bbs.domain.User;
import com.maple.bbs.service.ArticleService;
import com.maple.bbs.service.ReplyService;
import com.maple.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
public class ArticleController {
    @Autowired
    ArticleService articleService;
    @Autowired
    ReplyService replyService;
    @Autowired
    UserService userService;

    //发布文章api
    @PostMapping(value = "/new")
    public Result newArticle(HttpServletRequest request){
        Article article = new Article();
        article.setArticleTitle(request.getParameter("title"));
        article.setArticleBody(request.getParameter("body"));
        article.setAuthor(request.getParameter("userName"));
        article.setPostTime(new Date());
        article.setSort(request.getParameter("sort"));
        article.setLabel(request.getParameter("label"));
        int resultNum = articleService.newArticle(article);
        if(resultNum==0){
            return Result.resultMessage(200,"new article successfully");
        }else {
            return Result.resultMessage(500,"new error");
        }
    }

    //根据articleId获取文章内容
    @GetMapping(value = "/t/info")
    public Result getArticleContext(@RequestParam("articleId")String articleId){
        return Result.resultData(200,"success",articleService.queryArticle(articleId));
    }

    //获取文章回复api
    @GetMapping(value = "/t/{articleId}")
    public Result articleContent(@PathVariable("articleId")String articleId, @RequestParam(name = "page",required = false)String page){
        if(page==null){
            return Result.resultData(200,"success",replyService.queryReplyByArticleId(1,articleId));
        }else {
            int p = Integer.valueOf(page);
            return Result.resultData(200,"success",replyService.queryReplyByArticleId(p,articleId));
        }
    }

    //删除文章api
    @PostMapping(value = "/deleteArticle")
    public Result deleteArticle(@RequestParam("articleId")String articleId){
        try{
            articleService.deleteArticle(articleId);
            return Result.resultMessage(200,"delete success");
        }catch (Exception e){
            return Result.resultMessage(500,"delete error");
        }
    }

    //回复文章pi
    @PostMapping(value = "/t/{articleId}")
    public Result reply(@PathVariable("articleId")String articleId,HttpServletRequest request){
        User user = userService.queryInfo(request.getParameter("userName"));
        if(user.getUserState()==1){
            Date date = user.getBanTime();
            return Result.resultMessage(500,"sorry,you can't reply until"+date);
        }
        Reply reply = new Reply();
        reply.setArticleId(Long.valueOf(articleId));
        reply.setAuthor(request.getParameter("userName"));
        reply.setMessage(request.getParameter("message"));
        reply.setPostTime(new Date());
        int resultNum = replyService.reply(reply);
        if(resultNum==0){
            return Result.resultMessage(200,"reply successfully");
        }else {
            return Result.resultMessage(500,"reply error");
        }
    }

    //置顶文章api
    @PostMapping(value = "/t/{articleId}/top")
    public Result top(@PathVariable("articleId")String articleId){
        int resultNum = articleService.topArticle(articleId);
        if(resultNum==0){
            return Result.resultMessage(200,"top successfully");
        }else {
            return Result.resultMessage(400,"top error");
        }
    }

    //加精文章api
    @PostMapping(value = "/t/{articleId}/star")
    public Result start(@PathVariable("articleId")String articleId){
        int resultNum = articleService.starArticle(articleId);
        if(resultNum==0){
            return Result.resultMessage(200,"top successfully");
        }else {
            return Result.resultMessage(400,"top error");
        }
    }
    //获取用户所有文章
    @GetMapping(value = "/u/getAllArticle")
    public Result getAllArticleByUserName(@RequestParam("userName")String userName,@RequestParam(name = "page",required = false)String page){
        if(page!=null){
            return Result.resultData(200,"success",articleService.queryArticleByAuthor(userName,page));
        }else {
            return Result.resultData(200,"success",articleService.queryArticleByAuthor(userName,"1"));
        }
    }
    //获取所有普通文章api
    @GetMapping(value = "/article")
    public Result getAllArticle(@RequestParam(name = "page",required = false)String page){
        if(page!=null){
            return Result.resultData(200,"success",articleService.queryAllArticle(page));
        }else {
            return Result.resultData(200,"success",articleService.queryAllArticle("1"));
        }
    }

    //获取普通文章页数api
    @GetMapping(value = "/article/pageNum")
    public Result getAllArticlePageNum(){
        return Result.resultData(200,"success",articleService.articleNum("0")/20+1);
    }

    //根据文章Id获取回复页数
    @PostMapping(value = "/article/replyNum")
    public Result getReplyByArticleId(@RequestParam("articleId")String articleId){
        return Result.resultData(200,"success",replyService.replyNumByArticleId(articleId)/10+1);
    }
}
