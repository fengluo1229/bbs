package com.maple.bbs.mapper;

import com.maple.bbs.domain.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleMapper {
    List<Article> selectAllArticle();
    List<Article> selectArticlesBySortId(String sortId);
    int insertArticle(Article article);
    int updateArticle(Article article);
}
