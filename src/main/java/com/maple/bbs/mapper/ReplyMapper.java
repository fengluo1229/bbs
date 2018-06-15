package com.maple.bbs.mapper;

import com.maple.bbs.domain.Reply;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReplyMapper {
    List<Reply> selectAllReplyByArticleId(String articleId);
    int insertReply(Reply reply);
    int deleteReply(String replyId);
    int recoverReply(String replyId);
    int readReply(String replyId);
    List<Reply> queryDeleteReply();
    int replyNum(String replyState);
}
