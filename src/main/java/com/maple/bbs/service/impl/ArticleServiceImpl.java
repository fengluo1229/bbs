package com.maple.bbs.service.impl;

import com.github.pagehelper.PageHelper;
import com.maple.bbs.domain.Article;
import com.maple.bbs.domain.Reply;
import com.maple.bbs.mapper.ArticleMapper;
import com.maple.bbs.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleMapper articleMapper;

    @Override
    @Transactional
    public int newArticle(Article article) {
        try {
            articleMapper.insertArticle(article);
            return 0;
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    @Transactional
    public int topArticle(String articleId) {
        try {
            articleMapper.topArticle(articleId);
            return 0;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    @Transactional
    public int starArticle(String articleId) {
        try {
            articleMapper.starArticle(articleId);
            return 0;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    @Transactional
    public int deleteArticle(String articleId) {
        try {
            articleMapper.deleteArticle(articleId);
            return 0;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    @Transactional
    public int cancelDelete(String articleId) {
        try {
            articleMapper.cancelDelete(articleId);
            return 0;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    @Transactional
    public int cancelTop(String articleId) {
        try {
            articleMapper.cancelTop(articleId);
            return 0;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    @Transactional
    public int cancelStar(String articleId) {
        try {
            articleMapper.cancelStar(articleId);
            return 0;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public List<Article> queryAllDeleteArticle(String page) {
        PageHelper.startPage(Integer.valueOf(page),20);
        return articleMapper.queryAllDeleteArticle();
    }

    @Override
    public List<Article> queryAllTopArticle(String page) {
        PageHelper.startPage(Integer.valueOf(page),20);
        return articleMapper.queryAllTopArticle();
    }

    @Override
    public List<Article> queryAllStarArticle(String page) {
        PageHelper.startPage(Integer.valueOf(page),20);
        return articleMapper.queryAllStarArticle();
    }

    @Override
    public List<Article> queryAllArticle(String page) {
        PageHelper.startPage(Integer.valueOf(page),20);
        return articleMapper.queryAllArticle();
    }

    @Override
    public int articleNum(String articleState) {
        return articleMapper.articleNum(articleState);
    }

}
