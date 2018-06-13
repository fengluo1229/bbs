package com.maple.bbs.controller;

import com.maple.bbs.domain.Article;
import com.maple.bbs.domain.Reply;
import com.maple.bbs.domain.Result;
import com.maple.bbs.domain.User;
import com.maple.bbs.service.ArticleService;
import com.maple.bbs.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@CrossOrigin
public class ArticleController {
    @Autowired
    ArticleService articleService;
    @Autowired
    ReplyService replyService;
    @PostMapping(value = "/new")
    public Result newArticle(HttpServletRequest request){
        Article article = new Article();
        article.setArticleTitle(request.getParameter("title"));
        article.setArticleBody(request.getParameter("body"));
        article.setAuthor(((User) request.getSession().getAttribute("user")).getUserName());
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

    @GetMapping(value = "/t/{articleId}")
    public Result articleContent(@PathVariable("articleId")String articleId, @RequestParam(name = "page",required = false)String page){
        if(page==null){
            return Result.resultData(200,"success",replyService.queryReplyByArticleId(1,articleId));
        }else {
            int p = Integer.valueOf(page);
            return Result.resultData(200,"success",replyService.queryReplyByArticleId(p,articleId));
        }
    }

    @PostMapping(value = "/t/{articleId}")
    public Result reply(@PathVariable("articleId")String articleId,HttpServletRequest request){
        Reply reply = new Reply();
        reply.setArticleId(Long.valueOf(articleId));
        reply.setAuthor(((User)request.getSession().getAttribute("user")).getUserName());
        reply.setMessage(request.getParameter("message"));
        reply.setPostTime(new Date());
        int resultNum = replyService.reply(reply);
        if(resultNum==0){
            return Result.resultMessage(200,"reply successfully");
        }else {
            return Result.resultMessage(500,"reply error");
        }
    }

    @PostMapping(value = "/t/{articleId}/top")
    public Result top(@PathVariable("articleId")String articleId){
        int resultNum = articleService.topArticle(articleId);
        if(resultNum==0){
            return Result.resultMessage(200,"top successfully");
        }else {
            return Result.resultMessage(400,"top error");
        }
    }

    @PostMapping(value = "/t/{articleId}/star")
    public Result start(@PathVariable("articleId")String articleId){
        int resultNum = articleService.starArticle(articleId);
        if(resultNum==0){
            return Result.resultMessage(200,"top successfully");
        }else {
            return Result.resultMessage(400,"top error");
        }
    }
}
