package pers.jxy.dao;

import org.apache.ibatis.annotations.*;
import pers.jxy.entity.Note;

import java.util.LinkedHashSet;
import java.util.List;

public interface NoteDao {

    /**
     * 查询笔记部分信息
     */
    @Select("select n_no,n_name from note where nb_no=#{nbNo} and note_state=1")
    List<Note> queryNotes(@Param("nbNo") Integer nbNo);

    /**
     * 新建笔记
     */
    @Options(useGeneratedKeys = true, keyProperty = "nNo", keyColumn = "n_no ")
    @Insert("insert into note (n_name,nb_no,u_no,create_time) values (#{note.nName},#{note.nbNo},#{note.uNo},#{note.createTime})")
    Integer saveNote(@Param("note") Note note);

    /**
     * 存储笔记路径
     */
    @Insert("update note set n_body_url=#{path} where n_no=#{no} and note_state=1")
    Integer savePath(@Param("path") String path, @Param("no") Integer no);

    /**
     * 查询笔记信息
     */
    @Select("select n_no,\n" +
            "       n_name,\n" +
            "       n_good_num,\n" +
            "       n_read_num,\n" +
            "       note.n_collection_num\n" +
            "from note\n" +
            "where n_no=#{no}\n" +
            "and note_state=1;")
    Note queryNoteMessge(@Param("no") Integer no);

    /**
     * 查看笔记
     */
    @Select("select n_no,\n" +
            "       n_name,\n" +
            "       n_body_url,\n" +
            "       n_read_num,\n" +
            "       n_good_num,\n" +
            "       n_collection_num,\n" +
            "       n_comment_num,\n" +
            "       nb_no,\n" +
            "       u_no,\n" +
            "       note_state,\n" +
            "       (select is_good from user_note where user_no=#{uNo} and note_no=n_no) is_good,\n" +
            "       (select is_collection from user_note where user_no=#{uNo} and note_no=n_no) is_collection\n" +
            "from note " +
            "where n_no=#{no} and note_state=1")
    Note queryNote(@Param("no") Integer no, @Param("uNo") Integer uNo);

    /**
     * 查询作者账号
     */
    @Select("select u_no from note where n_no = #{nno}")
    Integer selectAuthorNo(@Param("nno") Integer nno);

    /**
     * 阅读数+1
     */
    @Update("update note set n_read_num=n_read_num+1 where n_no=#{no} and note_state=1")
    void read(@Param("no") Integer no);

    /**
     * 点赞数+1
     */
    @Update("update note set n_good_num=n_good_num+1 where n_no=#{no} and note_state=1")
    Boolean goGoodNumPlus(@Param("no") Integer no);

    /**
     * 点赞数-1
     */
    @Update("update note set n_good_num=n_good_num-1 where n_no=#{no} and note_state=1")
    Boolean goGoodNumReduce(@Param("no") Integer no);

    /**
     * 收藏数+1
     */
    @Update("update note set n_collection_num=n_collection_num+1 where n_no=#{no} and note_state=1")
    Boolean goCollecteNumPlus(@Param("no") Integer no);

    /**
     * 收藏数-1
     */
    @Update("update note set n_collection_num=n_collection_num-1 where n_no=#{no} and note_state=1")
    Boolean goCollecteNumReduce(@Param("no") Integer no);

    /**
     * 查询是否已有点赞收藏记录
     */
    @Select("select un_no from user_note where user_no=#{uNo} and note_no=#{nNo}")
    Integer getUnNo(@Param("uNo") Integer uNo, @Param("nNo") Integer nNo);

    /**
     * 添加点赞收藏记录
     */
    @Insert("insert into user_note " +
            "(user_no, note_no, un_type) " +
            "values (#{uNo},#{nNo},#{type})")
    Boolean addGoodCollectionRecord(@Param("uNo") Integer uNo, @Param("nNo") Integer nNo, @Param("type") Integer type);

    /**
     * 添加点赞记录
     */
    @Update("update user_note set is_good=1 where un_no=#{unNo}")
    Boolean addGoodRecord(@Param("unNo") Integer unNo);

    /**
     * 取消点赞记录
     */
    @Update("update user_note set is_good=0 where user_no=#{uNo} and note_no=#{nNo}")
    Boolean cancelGoodRecord(@Param("uNo") Integer uNo, @Param("nNo") Integer nNo);

    /**
     * 添加收藏记录
     */
    @Update("update user_note set is_collection=1 where un_no=#{unNo}")
    Boolean addCollectionRecord(@Param("unNo") Integer unNo);

    /**
     * 取消收藏记录
     */
    @Update("update user_note set is_collection=0 where user_no=#{uNo} and note_no=#{nNo}")
    Boolean cancelCollectionRecord(@Param("uNo") Integer uNo, @Param("nNo") Integer nNo);


    /**
     * 删除笔记(不可读)
     */
    @Update("update note set note_state= #{state} where n_no=#{no} ")
    Integer delNote(@Param("no") Integer no,@Param("state") Integer state);

    /**
     * 查询笔记保存路径
     */
    @Select("select n_body_url from note where n_no=#{no}")
    String getFilePath(@Param("no") Integer no);

    /**
     * 删除笔记（完全删除）
     */
    @Delete("delete from note where n_no=#{no}")
    Integer deleteNote(@Param("no") Integer no);

    /**
     * 精准搜索功能
     */
    @Select("select n_no,\n" +
            "       n_name,\n" +
            "       n_read_num,\n" +
            "       n_good_num,\n" +
            "       n_comment_num,\n" +
            "       u_no,\n" +
            "       (select nickname from user where no = u_no) u_name\n" +
            "from note\n" +
            "where n_name = #{name}\n" +
            "  and note_state = 1")
    List<Note> accurateSearchNote(@Param("name") String name);

    /**
     * 模糊查找
     */
    @Select("select n_no,\n" +
            "       n_name,\n" +
            "       n_read_num,\n" +
            "       n_good_num,\n" +
            "       n_comment_num,\n" +
            "       u_no,\n" +
            "       (select nickname from user where no = u_no) u_name\n" +
            "from note " +
            "where n_name like '${name}%' " +
            "and note_state = 1;")
    List<Note> fuzzySearchNote1(@Param("name") String name);

    @Select("select n_no,\n" +
            "       n_name,\n" +
            "       n_read_num,\n" +
            "       n_good_num,\n" +
            "       n_comment_num,\n" +
            "       u_no,\n" +
            "       (select nickname from user where no = u_no) u_name\n" +
            "from note " +
            "where n_name like '%${name}%' " +
            "and note_state = 1;")
    List<Note> fuzzySearchNote2(@Param("name") String name);


    /**
     * 查询用户收藏的笔记/笔记本的编号
     */
    @Select("select note_no\n" +
            "from user_note\n" +
            "where user_no = #{no}\n" +
            "  and un_type = #{type}")
    LinkedHashSet<Integer> getColNo(@Param("no") Integer no, @Param("type") Integer type);

    /**
     * 获取点赞的笔记
     */
    @Select("select note_no\n" +
            "from user_note\n" +
            "where user_no = #{no}\n" +
            "  and is_good = 1\n" +
            "  and user_note.un_type = 0;")
    List<Integer> getGoodNotes(@Param("no") Integer no);

    @Update("update note set n_name=#{nname} where n_no=#{nno};")
    Boolean changeNote(@Param("nname") String nname, @Param("nno") Integer nno);

    /**
     * 根据日期查询新增笔记数
     */
    @Select("select ifnull((select count(*) from note where create_time like '${time}%'),0);")
    Integer queryNewNoteNum(@Param("time") String time);

    /**
     * 热度排行查询
     */
    @Select("select n_no,\n" +
            "       n_name,\n" +
            "       (n_read_num + n_good_num * 3 + n_comment_num) hot_num\n" +
            "from note\n" +
            "order by hot_num desc\n" +
            "limit 0, 10;")
    List<Note> queryHot();

    /**
     * 查询是否已创建当天统计数
     */
    @Select("select count(v_date)\n" +
            "from visitNum where v_date=#{date}")
    Integer selectIsNull(@Param("date") String date);

    /**
     *
     */
    @Select("insert into visitNum\n" +
            "values (#{date}, 0)")
    Boolean insertDate(@Param("date") String date);

    /**
     * 阅读数加一
     */
    @Select("update visitNum\n" +
            "set v_num=v_num + 1\n" +
            "where v_date = #{date}")
    Boolean visitNumPlus(@Param("date") String date);

    /**
     * 查询仅七天访问量
     */
    @Select("select ifnull((select v_num from visitNum where v_date = #{date}),0)")
    Integer select7DayVNum(String date);
}
