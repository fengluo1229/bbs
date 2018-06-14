package com.maple.bbs.service;

import com.maple.bbs.domain.Article;
import com.maple.bbs.domain.Reply;

import java.util.Date;
import java.util.List;

public interface ArticleService {
    int newArticle(Article article);
    int topArticle(String articleId);
    int starArticle(String articleId);
    int deleteArticle(String articleId);
    int cancelDelete(String articleId);
    int cancelTop(String articleId);
    int cancelStar(String articleId);
    List<Article> queryAllDeleteArticle(String page);
    List<Article> queryAllTopArticle(String page);
    List<Article> queryAllStarArticle(String page);
    int starArticlePage();
    int deleteArticlePage();
}
