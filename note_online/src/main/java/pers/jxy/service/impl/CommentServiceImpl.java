package pers.jxy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.jxy.dao.CommentDao;
import pers.jxy.dao.MessageDao;
import pers.jxy.entity.Comment;
import pers.jxy.entity.Message;
import pers.jxy.service.CommentService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author:靳新宇
 * @date : 12-02 21:16
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentDao commentDao;

    @Autowired
    MessageDao messageDao;

    /**
     * 查询评论
     *
     * @param page:查询第多少也的评论 首先根据页数计算出开始编号再根据文章编号和开始序号进行查询
     */
    @Override
    public List<Comment> queryComment(Integer nNo, Integer page) {
        return commentDao.queryComment(nNo, page * 10 - 10);
    }

    /**
     * 文章评论功能（添加评论），且在评论时给文章添加评论数
     * @param comment:评论信息
     */
    @Override
    public Boolean addComment(Comment comment) {
        Integer res = commentDao.addComment(comment);
        Message message = new Message();
        message.setMBody(comment.getCContent());
        message.setMToWhoNo(comment.getToNo());
        message.setMWhoToNo(comment.getUNo());
        message.setMType(1);
        messageDao.leaveMessage(message);
        commentDao.commentNum(comment.getCToNno());
        return res > 0;
    }
}
