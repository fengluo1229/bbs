package com.maple.bbs.service.impl;

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
            int resultNum = articleMapper.insertArticle(article);
            return 0;
        } catch (Exception e) {
            return -1;
        }
    }
}
