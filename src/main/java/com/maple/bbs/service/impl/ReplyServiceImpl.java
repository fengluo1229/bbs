package com.maple.bbs.service.impl;

import com.github.pagehelper.PageHelper;
import com.maple.bbs.domain.Reply;
import com.maple.bbs.mapper.ArticleMapper;
import com.maple.bbs.mapper.ReplyMapper;
import com.maple.bbs.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    ReplyMapper replyMapper;
    @Autowired
    ArticleMapper articleMapper;
    @Override
    public List<Reply> queryReplyByArticleId(int page, String articleId) {
        PageHelper.startPage(page,10);
        List<Reply> replies= replyMapper.selectAllReplyByArticleId(articleId);
        return replies;
    }

    @Override
    @Transactional
    public int reply(Reply reply) {
        try {
            int resultNum = replyMapper.insertReply(reply);
            int resultNum2 = articleMapper.updateLastReply(reply);
            return 0;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }
}
