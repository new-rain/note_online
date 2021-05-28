package pers.jxy.dao;

import org.apache.ibatis.annotations.*;
import pers.jxy.entity.Message;

import java.util.ArrayList;

@Mapper
public interface MessageDao {
    /**
     * 发送信息
     */
    @Insert("insert into message (m_body,m_to_who_no,m_who_to_no,m_type,m_state,note_no) values (#{message.mBody},#{message.mToWhoNo}, #{message.mWhoToNo},#{message.mType},0,#{message.noteNo});")
    Boolean leaveMessage(@Param("message") Message message);

    /**
     * 根据账号查询信息
     */
    @Select("select * from message where m_no= #{ no }")
    Message selectByNo(@Param("no")Integer no);

    /**
     * 获取本人收到的信息
     */
    @Select("select m_no,\n" +
            "       m_body,\n" +
            "       m_type,\n" +
            "       m_state,\n" +
            "       m_time,\n" +
            "       note_no,\n" +
            "       m_who_to_no,\n" +
            "       (select nickname from user where no = m_who_to_no) sender,\n" +
            "       (select n_name from note where n_no = note_no)     note_name\n" +
            "from message\n" +
            "where m_to_who_no = #{ no }\n" +
            "  and m_type = #{type}\n" +
            "order by m_state asc, m_time desc")
    ArrayList<Message> getMessages(@Param("no") Integer no, @Param("type") Integer type);

    @Select("select m_no,\n" +
            "       m_body,\n" +
            "       m_type,\n" +
            "       m_state,\n" +
            "       m_time,\n" +
            "       m_who_to_no,\n" +
            "       (select nickname from user where no = m_who_to_no) sender\n" +
            "from message\n" +
            "where m_to_who_no = #{ no }\n" +
            "  and m_type in (4, 5)\n" +
            "order by m_state, m_time desc")
    ArrayList<Message> getMessagesTwo(@Param("no") Integer no);


    /**
     * 获取收到的信息数
     */
    @Select("select count(*)\n" +
            "from message\n" +
            "where m_to_who_no = #{no}\n" +
            "  and m_type = #{type}")
    Integer selectMsgNum(@Param("no") Integer no, @Param("type") Integer type);

    /**
     * 一键已读
     */
    @Update("update message\n" +
            "set m_state=1\n" +
            "where m_to_who_no = #{no}\n" +
            "  and m_type = #{type}")
    Integer readAll(@Param("no") Integer no, @Param("type") Integer type);

    /**
     * 设置信息状态为已读
     */
    @Update("update message set m_state=1 where m_no=#{no}")
    Boolean checkMsg(@Param("no") Integer no);

    /**
     * 清除所有信息
     */
    @Delete("delete from message where m_to_who_no=#{no} and m_type=#{type}")
    Integer deleteAll(@Param("no") Integer no, @Param("type") Integer type);

    /**
     * 删除信息
     */
    @Delete("delete from message where m_no=#{no}")
    Boolean deleteMsg(@Param("no") Integer no);

    /**
     * 查询有多少新信息
     */
    @Select("select count(*) from message where m_to_who_no=#{no} and m_type=#{type} and m_state=0")
    Integer checkHaveNew(@Param("no") Integer no, @Param("type") Integer type);


}
