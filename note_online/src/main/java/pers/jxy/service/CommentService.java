package pers.jxy.service;

import pers.jxy.entity.Comment;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface CommentService {

    Boolean addComment(Comment comment);

    List<Comment> queryComment(Integer nNo, Integer page);
}
