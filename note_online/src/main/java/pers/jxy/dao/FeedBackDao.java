package pers.jxy.dao;

import org.apache.ibatis.annotations.*;
import pers.jxy.entity.Feedback;

import java.util.ArrayList;


/**
 * @author:靳新宇
 * @date : 02-24 13:56
 */
@Mapper
public interface FeedBackDao {
    @Insert("insert into feedback (f_type, f_body, fu_no, f_time) " +
            "values (#{feedback.fType},#{feedback.fBody},#{feedback.fuNo},#{feedback.fTime})")
    Boolean feedback(@Param("feedback") Feedback feedBack);

    @Select("select f_no,\n" +
            "       f_type,\n" +
            "       f_body,\n" +
            "       fu_no,\n" +
            "       (select nickname from user where no = fu_no) fu_name,\n" +
            "       f_time,\n" +
            "       f_state\n" +
            "from feedback\n" +
            "where f_state = #{state}\n" +
            "order by f_time desc")
    ArrayList<Feedback> getFeedbacks(@Param("state") Integer state);

    @Select("select count(*) from feedback")
    Integer feedbackNum();

    @Update("update feedback set f_state = 1 where f_no = #{ no }")
    Boolean updateState(@Param("no") Integer no);
}
