package com.maple.bbs.controller;

import com.maple.bbs.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class IndexController {

    @Autowired
    ArticleService articleService;

}
