package com.maple.bbs.controller;

import com.maple.bbs.domain.Result;
import com.maple.bbs.domain.User;
import com.maple.bbs.mapper.ArticleMapper;
import com.maple.bbs.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class IndexController {

    @Autowired
    ArticleService articleService;

    @GetMapping(value = "/")
    public Result indexController(HttpServletRequest request) {
        if (request.getSession().getAttribute("user") != null) {
            User user = (User) request.getSession().getAttribute("user");

        }
        return Result.resultMessage(200, "error");
    }
}
