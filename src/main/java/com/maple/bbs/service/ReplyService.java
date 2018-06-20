package com.maple.bbs.service;

import com.maple.bbs.domain.Reply;

import java.util.List;

public interface ReplyService {
    List<Reply> queryReplyByArticleId(int page,String articleId);
    int reply(Reply reply);
    List<Reply> queryDeleteReply(String page);
    int recoverReply(String replyId);
    int replyNum(String replyState);
    int replyNumByArticleId(String articleId);
}
