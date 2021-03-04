package pers.jxy.dao;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import pers.jxy.entity.NoteBook;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface NoteBookDao {
    /**
     * 新建笔记本
     */
    @Options(useGeneratedKeys = true, keyProperty = "bNo", keyColumn = "b_no ")
    @Insert("insert into notebook (b_no, b_name, b_describe, b_type, b_power, b_create_time, b_update_time, u_no, notebook_state, cover_url) " +
            " values (null,#{noteBook.bName},#{noteBook.bDescribe},#{noteBook.bType},#{noteBook.bPower},#{noteBook.bCreateTime},#{noteBook.bUpdateTime},#{noteBook.uNo},1,#{noteBook.coverUrl})")
    void newNoteBook(@Param("noteBook") NoteBook noteBook);

    @Select("select * from notebook where u_no=#{no} order by b_update_time and notebook_state = 1")
    List<NoteBook> queryAllNoteBooks(@Param("no") Integer no);

    @Delete("delete from notebook where b_no=#{bNo} and notebook_state = 1")
    Integer deleteNoteBook(@Param("bNo") Integer bNo);

    @Select("select b_no,\n" +
            "       b_name,\n" +
            "       b_describe,\n" +
            "       b_type,\n" +
            "       b_create_time,\n" +
            "       b_update_time,\n" +
            "       u_no,\n" +
            "       ifnull((select sum(n_read_num) from note where nb_no = b_no),0)    b_read_num,\n" +
            "       ifnull((select sum(n_good_num) from note where nb_no = b_no),0)    b_good_num,\n" +
            "       ifnull((select count(*) from user_note where note_no = b_no),0) b_collection_num,\n" +
            "       cover_url\n" +
            "from notebook\n" +
            "where b_no = #{no}\n" +
            "  and notebook_state = 1")
    NoteBook getNoteBook(@Param("no") Integer no);

    @Select("select b_no,\n" +
            "       b_name,\n" +
            "       b_describe,\n" +
            "       b_create_time,\n" +
            "       u_no,\n" +
            "       (select nickname from user where no=u_no) uname,\n" +
            "       ifnull((select sum(n_read_num) from note where nb_no = b_no), 0) b_read_num,\n" +
            "       ifnull((select sum(n_good_num) from note where nb_no = b_no), 0) b_good_num,\n" +
            "       ifnull((select count(*) from user_note where note_no = b_no), 0) b_collection_num,\n" +
            "       cover_url\n" +
            "from notebook\n" +
            "where b_no = #{no}\n" +
            "  and notebook_state = 1")
    NoteBook getNoteBook2(@Param("no") Integer no);

    /**
     * 修改笔记本信息
     */
    @Update("update notebook set b_name=#{noteBook.bName},b_describe=#{noteBook.bDescribe},b_type=#{noteBook.bType},b_power=#{noteBook.bPower},cover_url=#{noteBook.coverUrl} where b_no=#{noteBook.bNo} and notebook_state = 1")
    Integer updateNoteBook(@Param("noteBook") NoteBook noteBook);

    /**
     * 一对多查询笔记本信息
     */
    @Select("select b_no,\n" +
            "       b_name,\n" +
            "       b_describe,\n" +
            "       b_type,\n" +
            "       b_create_time,\n" +
            "       b_update_time,\n" +
            "       (select nickname from user where no=u_no) uname,\n" +
            "       ifnull((select sum(n_read_num) from note where nb_no = b_no), 0)                          b_read_num,\n" +
            "       ifnull((select sum(n_good_num) from note where nb_no = b_no), 0)                          b_good_num,\n" +
            "       ifnull((select sum(n_comment_num) from note where nb_no = b_no), 0)                       b_comment_num,\n" +
            "       ifnull((select count(*) from note where nb_no = b_no), 0)                                 b_note_num,\n" +
            "       ifnull((select is_collection from user_note where user_no = #{uNo} and note_no = b_no), 0) is_collection,\n" +
            "       u_no,\n" +
            "       cover_url\n" +
            "from notebook\n" +
            "where b_no = #{bNo}\n" +
            "  and notebook_state = 1;")
    @Results(id = "notebook_note", value = {
            @Result(column = "b_no", property = "bNo", id = true),
            @Result(column = "b_name", property = "bName"),
            @Result(column = "b_describe", property = "bDescribe"),
            @Result(column = "b_type", property = "bType"),
            @Result(column = "b_power", property = "bPower"),
            @Result(column = "b_create_time", property = "bCreateTime"),
            @Result(column = "b_update_time", property = "bUpdateTime"),
            @Result(column = "b_read_num", property = "bReadNum"),
            @Result(column = "b_good_num", property = "bGoodNum"),
            @Result(column = "b_comment_num", property = "bCommentNum"),
            @Result(column = "b_note_num", property = "bNoteNum"),
            @Result(column = "u_no", property = "uNo"),
            @Result(column = "cover_url", property = "coverUrl"),
            @Result(column = "is_good", property = "isGood"),
            @Result(column = "is_collection", property = "isCollection"),
            @Result(property = "notes", column = "b_no",
                    many = @Many(select = "pers.jxy.dao.NoteDao.queryNotes", fetchType = FetchType.LAZY))
    })
    NoteBook queryNoteBookMenu(@Param("bNo") Integer bNo, @Param("uNo") Integer uNo);

    /**
     * 时间正序查询
     */
    @Select("select distinct b_create_time from notebook where u_no=#{no} and notebook_state = 1 order by b_create_time")
    List<String> queryTimeType(@Param("no") Integer no);

    /**
     * 时间倒序查询
     */
    @Select("select distinct b_create_time\n" +
            "from notebook\n" +
            "where u_no = #{no}\n" +
            "  and notebook_state = 1\n" +
            "order by b_create_time desc")
    List<String> queryTimeTypeDesc(@Param("no") Integer no);

    /**
     * 根据时间查找笔记本
     */
    @Select("select *\n" +
            "from notebook\n" +
            "where b_create_time like '${date}%'\n" +
            "  and u_no = #{no}\n" +
            "  and notebook_state = 1")
    List<NoteBook> queryByTime(@Param("date") String date, @Param("no") Integer no);

    /**
     * 根据时间倒序查询笔记本
     */
    @Select("select b_no,b_name,b_describe,b_create_time,b_update_time,ifnull((select sum(n_read_num) from note where nb_no = b_no), 0) b_read_num,ifnull((select sum(n_good_num) from note where nb_no = b_no), 0) b_good_num,ifnull((select sum(n_comment_num) from note where nb_no = b_no), 0) b_comment_num,cover_url from notebook where u_no=#{no} and notebook_state = 1 order by b_create_time")
    List<NoteBook> queryNoteBookByTimeAsc(@Param("no") Integer no);

    /**
     * 根据时间正序查询笔记本
     */
    @Select("select b_no,b_name,b_describe,b_create_time,b_update_time,ifnull((select sum(n_read_num) from note where nb_no = b_no), 0) b_read_num,ifnull((select sum(n_good_num) from note where nb_no = b_no), 0) b_good_num,ifnull((select sum(n_comment_num) from note where nb_no = b_no), 0) b_comment_num,cover_url from notebook where u_no=#{no} and notebook_state = 1 order by b_create_time desc")
    List<NoteBook> queryNoteBookByTimeDesc(@Param("no") Integer no);


    /**
     * 根据阅读量查询笔记本
     */
    @Select("select b_no,b_name,b_describe,b_create_time,b_update_time,ifnull((select sum(n_read_num) from note where nb_no = b_no), 0) b_read_num,ifnull((select sum(n_good_num) from note where nb_no = b_no), 0) b_good_num,ifnull((select sum(n_comment_num) from note where nb_no = b_no), 0) b_comment_num,cover_url from notebook where u_no=#{no} and notebook_state = 1 order by b_read_num desc")
    List<NoteBook> queryNoteBookByReadNum(@Param("no") Integer no);


    /**
     * 根据点赞量查询笔记本
     */
    @Select("select b_no,b_name,b_describe,b_create_time,b_update_time,ifnull((select sum(n_read_num) from note where nb_no = b_no), 0) b_read_num,ifnull((select sum(n_good_num) from note where nb_no = b_no), 0) b_good_num,ifnull((select sum(n_comment_num) from note where nb_no = b_no), 0) b_comment_num,cover_url from notebook where u_no=#{no} and notebook_state = 1 order by b_good_num desc")
    List<NoteBook> queryNoteBookByGoodNum(@Param("no") Integer no);

    /**
     * 根据综合热度查询笔记本
     */
    @Select("select b_no,\n" +
            "       b_name,\n" +
            "       b_describe,\n" +
            "       b_create_time,\n" +
            "       b_update_time,\n" +
            "       ifnull((select sum(n_read_num) from note where nb_no = b_no), 0)          b_read_num,\n" +
            "       ifnull((select sum(n_good_num) from note where nb_no = b_no), 0)          b_good_num,\n" +
            "       ifnull((select sum(n_comment_num) from note where nb_no = b_no), 0)       b_comment_num,\n" +
            "       (ifnull((select sum(n_read_num) from note where nb_no = b_no), 0) +\n" +
            "        ifnull((select sum(n_good_num) from note where nb_no = b_no), 0) * 2 +\n" +
            "        ifnull((select sum(n_comment_num) from note where nb_no = b_no), 0) * 3) hot,\n" +
            "       cover_url\n" +
            "from notebook\n" +
            "where u_no = #{no}\n" +
            "  and notebook_state = 1\n" +
            "order by hot desc")
    List<NoteBook> queryNoteBookByHot(@Param("no") Integer no);


    /**
     * 查询所有笔记本标签
     */
    @Select("select b_type from notebook where u_no=#{no} and notebook_state = 1")
    ArrayList<String> selectAllTypes(@Param("no") Integer no);

    /**
     * 根据标签查找笔记
     */
    @Select("select * from notebook where b_type like '%${type}%' and notebook_state = 1")
    ArrayList<NoteBook> selectNotebookByType(@Param("type") String type);

    /**
     * 精准搜索功能
     */
    @Select("select b_no,b_name,b_describe,b_type,b_power,b_create_time,b_update_time,(select sum(n_read_num) from note where nb_no = b_no) b_read_num,(select sum(n_good_num) from note where nb_no = b_no) b_good_num,(select sum(n_comment_num) from note where nb_no = b_no) b_comment_num,(select count(*) from note where nb_no=b_no) b_note_num,u_no,cover_url from notebook where b_name = #{name} and notebook_state = 1")
    List<NoteBook> accurateSearchNoteBook(@Param("name") String name);

    /**
     * 模糊查找
     */
    @Select("select b_no,b_name,b_describe,b_type,b_power,b_create_time,b_update_time,(select sum(n_read_num) from note where nb_no = b_no) b_read_num,(select sum(n_good_num) from note where nb_no = b_no) b_good_num,(select sum(n_comment_num) from note where nb_no = b_no) b_comment_num,(select count(*) from note where nb_no=b_no) b_note_num,u_no,cover_url from notebook where b_name like '${name}%' and notebook_state = 1")
    List<NoteBook> fuzzySearchNoteBook1(@Param("name") String name);

    @Select("select b_no,b_name,b_describe,b_type,b_power,b_create_time,b_update_time,(select sum(n_read_num) from note where nb_no = b_no) b_read_num,(select sum(n_good_num) from note where nb_no = b_no) b_good_num,(select sum(n_comment_num) from note where nb_no = b_no) b_comment_num,(select count(*) from note where nb_no=b_no) b_note_num,u_no,cover_url from notebook where b_name like '%${name}%' and notebook_state = 1")
    List<NoteBook> fuzzySearchNoteBook2(@Param("name") String name);

    @Select("select b_no,b_name,b_describe,b_type,b_power,b_create_time,b_update_time,(select sum(n_read_num) from note where nb_no = b_no) b_read_num,(select sum(n_good_num) from note where nb_no = b_no) b_good_num,(select sum(n_comment_num) from note where nb_no = b_no) b_comment_num,(select count(*) from note where nb_no=b_no) b_note_num,u_no,cover_url from notebook where b_name like '%${name}' and notebook_state = 1")
    List<NoteBook> fuzzySearchNoteBook3(@Param("name") String name);
}

