package pers.jxy.dao;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import pers.jxy.entity.Comment;

import java.util.List;

@Mapper
public interface CommentDao {


    @Select("select c_no,\n" +
            "       c_content,\n" +
            "       c_to_nno,\n" +
            "       u_no,\n" +
            "       u_name,\n" +
            "       to_no,\n" +
            "       to_name,\n" +
            "       c_type,\n" +
            "       c_time\n" +
            "from comment where c_to_nno=#{nNo} limit #{beginNum} , 10")
    @Results(id = "comment", value = {
            @Result(column = "c_no", property = "cNo", id = true),
            @Result(column = "c_content", property = "cContent"),
            @Result(column = "c_to_nno", property = "cToNno"),
            @Result(column = "u_no", property = "uNo"),
            @Result(column = "u_name", property = "uName"),
            @Result(column = "c_type", property = "cType"),
            @Result(column = "c_time", property = "cTime"),
            @Result(property = "comments", column = "c_no",
                    many = @Many(select = "pers.jxy.dao.CommentDao.queryCommentReply", fetchType = FetchType.LAZY))
    })
    List<Comment> queryComment(@Param("nNo") Integer nNo, @Param("beginNum") Integer beginNum);

    @Select("select c_no,\n" +
            "       c_content,\n" +
            "       c_to_nno,\n" +
            "       u_no,\n" +
            "       u_name,\n" +
            "       to_no,\n" +
            "       to_name,\n" +
            "       c_type,\n" +
            "       c_time\n" +
            "from comment\n" +
            "where c_type > 1\n" +
            "  and c_to_nno = #{cNo} order by c_time;")
    List<Comment> queryCommentReply(@Param("cNo") Integer cNo);


    /**
     * 评论时，对文章评论数加一
     */
    @Update("update note set n_comment_num=n_comment_num+1 where n_no=#{no}")
    void commentNum(@Param("no") Integer no);

    /**
     * 添加评论
     */
    @Insert("insert into comment (c_content, c_to_nno, u_no, u_name, to_no, to_name, c_type) values (#{comment.cContent}, #{comment.cToNno}, #{comment.uNo}, #{comment.uName}, #{comment.toNo}, #{comment.toName}, #{comment.cType})")
    Integer addComment(@Param("comment") Comment comment);
}