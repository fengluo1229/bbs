package com.maple.bbs.controller;

import com.maple.bbs.domain.Article;
import com.maple.bbs.domain.Result;
import com.maple.bbs.domain.User;
import com.maple.bbs.mapper.ArticleMapper;
import com.maple.bbs.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    ArticleService articleService;

    @GetMapping(value = "/")
    public String indexController(ModelMap modelMap) {
        List<Article> topArticle= articleService.queryAllTopArticle("1");
        modelMap.put("topArticle",topArticle);
        return "index";
    }

//    @GetMapping(value = "/login.html")
//    public String loginController(){
//        return "login";
//    }
}
