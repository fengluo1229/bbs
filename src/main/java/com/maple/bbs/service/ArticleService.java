package com.maple.bbs.service;

import com.maple.bbs.domain.Article;
import com.maple.bbs.domain.Reply;

import java.util.Date;
import java.util.List;

public interface ArticleService {
    int newArticle(Article article);
}
