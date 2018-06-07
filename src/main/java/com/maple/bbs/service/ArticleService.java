package com.maple.bbs.service;

import com.maple.bbs.domain.Article;

import java.util.List;

public interface ArticleService {
    List<Article> queryAllArticle();
    List<Article> queryAllArticleBy();
}
