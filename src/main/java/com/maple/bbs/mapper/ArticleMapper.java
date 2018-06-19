package com.maple.bbs.mapper;

import com.maple.bbs.domain.Article;
import com.maple.bbs.domain.Reply;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface ArticleMapper {
    //List<Article> selectAllArticle();
    //List<Article> selectArticlesBySortId(String sortId);
    int insertArticle(Article article);

    int updateLastReply(Reply reply);

    int topArticle(String articleId);

    int starArticle(String articleId);

    int deleteArticle(String articleId);

    int cancelDelete(String articleId);

    int cancelTop(String articleId);

    int cancelStar(String articleId);

    List<Article> queryAllDeleteArticle();

    List<Article> queryAllTopArticle();

    List<Article> queryAllStarArticle();

    int articleNum(String articleString);

    List<Article> queryAllArticle();

    Article queryArticle(String articleId);

    List<Article> queryArticlesByAuthor(String author);
}
