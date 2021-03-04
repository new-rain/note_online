package pers.jxy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.jxy.entity.Comment;
import pers.jxy.service.CommentService;

import java.util.List;

/**
 * @author:靳新宇
 * @date : 12-02 21:23
 * 评论功能控制层
 */
@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    /**
     * 根据序号获取某篇文章的第几页评论
     *
     * @param nNo:文章编号
     * @param page:查询页数
     */
    @GetMapping("/queryComment")
    public List<Comment> queryComment(Integer nNo, Integer page) {
        return commentService.queryComment(nNo, page);
    }

    /**
     * 文章评论功能
     */
    //c_content, c_to_nno, u_no, u_name,  c_type, c_time
    @PostMapping("addComment")
    public Boolean addComment(String content, Integer nNo, Integer uNo, String uName, Integer toNo, String toName, Integer cType) {
        Comment comment = new Comment();
        comment.setCContent(content);
        comment.setCToNno(nNo);
        comment.setUNo(uNo);
        comment.setUName(uName);
        comment.setToNo(toNo);
        comment.setToName(toName);
        comment.setCType(cType);
        return commentService.addComment(comment);
    }
}
