package pers.jxy.service;

import org.springframework.transaction.annotation.Transactional;
import pers.jxy.entity.Comment;

import java.util.List;

@Transactional(readOnly = true)
public interface CommentService {
    @Transactional(readOnly = false)
    Boolean addComment(Comment comment);

    List<Comment> queryComment(Integer nNo, Integer page);
}
