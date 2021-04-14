package pers.jxy.dao;

import org.apache.ibatis.annotations.*;
import pers.jxy.entity.User;

import java.util.List;

@Mapper
public interface UserDao {

    /**
     * 查询邮箱是否已注册
     */
    @Select("select no from user where email=#{email}")
    Integer selectExist(@Param("email") String email);

    /**
     * 新增用户，并返回主键
     */
    @Options(useGeneratedKeys = true, keyProperty = "no", keyColumn = "no")
    @Insert("insert into user (nickname,password,email,headUrl,create_time)" +
            " values (#{user.nickname},#{user.password},#{user.email},#{user.headUrl},#{user.createTime})")
    Integer register(@Param("user") User user);

    /**
     * 封禁帐号
     */
    @Update("update user set state = 0 where no = #{no}")
    Boolean banUser(@Param("no") Integer no);

    /**
     * 用户登录，并返回用户信息
     */
    @Select("select * from user where no = #{no} and password=#{password} and state = 1")
    User login(Integer no, String password);

    /**
     * 根据邮箱验证修改密码
     */
    @Update("update user set password = #{user.password} where no=#{user.no}")
    Boolean updatePassword(@Param("user") User user);

    /**
     * 修改个人信息
     */
    @Update("update user set nickname = #{user.nickname},brief= #{user.brief},name= #{user.name},birthday= #{user.birthday}," +
            "gender= #{user.gender},job= #{user.job},address= #{user.address},now_address= #{user.nowAddress} where no=#{user.no}")
    Integer updateUser(@Param("user") User user);

    /**
     * 获取文章作者部分信息
     */
    @Select("select no,\n" +
            "       nickname,\n" +
            "       headUrl,\n" +
            "       create_time,\n" +
            "       job,\n" +
            "       ifnull((select count(*) from notebook where u_no=no),0) notebook_num,\n" +
            "       ifnull((select sum(n_good_num) from note where u_no=no),0)  get_good_num,\n" +
            "       ifnull((select sum(n_read_num) from note where u_no=no),0)  watch_num\n" +
            "from user\n" +
            "where no = #{no}")
    User getAuthor(@Param("no") Integer no);

    /**
     * 查询用户关系
     */
    @Select("select ifnull((select r_relationship\n" +
            "from user_relationship\n" +
            "where a_no = #{aNo}\n" +
            "  and b_no = #{bNo}),0)")
    Integer selectUserRelationship(@Param("aNo") Integer aNo, @Param("bNo") Integer bNo);

    /**
     * 查询互相关注的人
     */
    @Select("select b_no from user_relationship where r_relationship=1 and a_no=#{no}")
    List<Integer> selectEachOther1(@Param("no") Integer no);

    @Select("select a_no from user_relationship where r_relationship=1 and b_no=#{no}")
    List<Integer> selectEachOther2(@Param("no") Integer no);

    /**
     * 查询关注我的用户
     */
    @Select("select a_no from user_relationship where r_relationship=2 and b_no=#{no}")
    List<Integer> selectCareMe(@Param("no") Integer no);

    /**
     * 查询我关注的用户
     */
    @Select("select b_no from user_relationship where r_relationship=2 and a_no=#{no}")
    List<Integer> selectMyCare(@Param("no") Integer no);

    /**
     * 查询用户简单信息
     */
    @Select("select no,nickname,brief,headUrl from user where no=#{no}")
    User getUserMsg(@Param("no") Integer no);

    /**
     * 新建关系
     */
    @Insert("insert into user_relationship (a_no,b_no,r_relationship) values (#{aNo}, #{bNo}, 2)")
    Boolean addRelationship(@Param("aNo") Integer aNo, @Param("bNo") Integer bNo);

    /**
     * 关注我的 -> 互相关注
     */
    @Update("update user_relationship set r_relationship = 1 where a_no = #{aNo} and b_no = #{bNo} ")
    Boolean goEach(@Param("aNo") Integer aNo, @Param("bNo") Integer bNo);

    /**
     * 互相关注 -> 关注我的
     * b为本人,使用本方法
     * b为他人,删除关系，新建关系
     */
    @Update("update user_relationship set r_relationship = 2 where a_no = #{aNo} and b_no = #{bNo} ")
    Integer cancelEach(@Param("aNo") Integer aNo, @Param("bNo") Integer bNo);

    /**
     * 查询相互关注的两个关系用户
     */
    @Select("select r_no from user_relationship where a_no = #{aNo} and b_no = #{bNo}")
    Integer selectEachOrder(@Param("aNo") Integer aNo, @Param("bNo") Integer bNo);

    /**
     * 我的关注 -> 无关系
     */
    @Delete("delete from user_relationship where a_no = #{aNo} and b_no = #{bNo}")
    Integer delRelationship(@Param("aNo") Integer aNo, @Param("bNo") Integer bNo);


    /**
     * 获取好友信息
     */
    @Select("select no, nickname, email, brief,headUrl,create_time,\n" +
            "       ifnull((select count(*) from notebook where u_no=no),0) notebook_num,\n" +
            "       ifnull((select count(*) from note where u_no=no),0) note_num\n" +
            "from user where no =#{no}")
    User getFriendMsg(@Param("no") Integer no);

    /**
     * 获取账号笔记信息
     */
    @Select("select ifnull((select sum(n_read_num) from note where u_no = no), 0) watch_num,\n" +
            "       ifnull((select count(*) from notebook where u_no = no), 0)    notebook_num,\n" +
            "       ifnull((select count(*) from note where u_no = no), 0)        note_num,\n" +
            "       ifnull((select sum(n_good_num) from note), 0)                 get_good_num,\n" +
            "       ifnull((select sum(n_comment_num) from note), 0)              get_comment_num\n" +
            "from user\n" +
            "where no = #{no};")
    User getUserNoteMsg(@Param("no") Integer no);

    /**
     * 搜索用户
     */
    @Select("select no,\n" +
            "       nickname,\n" +
            "       brief,\n" +
            "       headUrl,\n" +
            "       ifnull((select if((select r_relationship from user_relationship where a_no = no and b_no = #{no}) = 1, 1,\n" +
            "                         (select r_relationship from user_relationship where a_no = #{no} and b_no = no))),\n" +
            "              0) friend_re\n" +
            "from user\n" +
            "where no = #{keyWord}")
    User searchUserByNo(@Param("keyWord") String keyWord, @Param("no") Integer no);


    @Select("select no,\n" +
            "       nickname,\n" +
            "       brief,\n" +
            "       headUrl,\n" +
            "       ifnull((select if((select r_relationship from user_relationship where a_no = no and b_no = #{no}) = 1, 1,\n" +
            "                         (select r_relationship from user_relationship where a_no = #{no} and b_no = no))),\n" +
            "              0) friend_re\n" +
            "from user\n" +
            "where nickname = #{keyWord}")
    List<User> searchUserByKeyWord(@Param("keyWord") String keyWord, @Param("no") Integer no);

    @Select("select no,\n" +
            "       nickname,\n" +
            "       brief,\n" +
            "       headUrl,\n" +
            "       ifnull((select if((select r_relationship from user_relationship where a_no = no and b_no = #{no}) = 1, 1,\n" +
            "                         (select r_relationship from user_relationship where a_no = #{no} and b_no = no))),\n" +
            "              0) friend_re\n" +
            "from user\n" +
            "where nickname like '${keyWord}%'")
    List<User> searchUserByKeyWord1(@Param("keyWord") String keyWord, @Param("no") Integer no);

    @Select("select no,\n" +
            "       nickname,\n" +
            "       brief,\n" +
            "       headUrl,\n" +
            "       ifnull((select if((select r_relationship from user_relationship where a_no = no and b_no = #{no}) = 1, 1,\n" +
            "                         (select r_relationship from user_relationship where a_no = #{no} and b_no = no))),\n" +
            "              0) friend_re\n" +
            "from user\n" +
            "where nickname like '%${keyWord}%'")
    List<User> searchUserByKeyWord2(@Param("keyWord") String keyWord, @Param("no") Integer no);

    @Update("update user set headUrl=#{headUrl} where no=#{no};")
    Boolean updateHeadUrl(@Param("headUrl") String headUrl, @Param("no") Integer no);

    /**
     * 查询某天创建账号数
     */
    @Select("select count(*)\n" +
            "from user where create_time like '%${date}%'")
    Integer selectRegisterNum(@Param("date") String date);

    /**
     * 查询热度前八
     */
    @Select("select no,\n" +
            "       nickname,\n" +
            "       ifnull((select sum(n_read_num + n_good_num * 2 + n_comment_num * 3) from note where u_no = no), 0) hot_num\n" +
            "from user\n" +
            "order by hot_num desc\n" +
            "limit 0,8;")
    List<User> getHotUser();

    /**
     * 查询点击量前八
     */
    @Select("select nickname,\n" +
            "       ifnull((select sum(n_read_num) from note where u_no = no), 0) watch_num\n" +
            "from user\n" +
            "order by watch_num desc\n" +
            "limit 0,8")
    List<User> readTop8();

    /**
     * 查询获赞量前8
     */
    @Select("select nickname,\n" +
            "    ifnull((select sum(n_good_num) from note where u_no = no), 0) get_good_num\n" +
            "    from user\n" +
            "    order by get_good_num desc\n" +
            "    limit 0,8")
    List<User> goodTop8();

    /**
     * 查询获赞量前8
     */
    @Select("select nickname,\n" +
            "       ifnull((select count(r_no) from user_relationship where a_no = no or b_no = no and r_relationship = 1), 0) num1,\n" +
            "       ifnull((select count(r_no) from user_relationship where r_relationship = 2 and b_no = no), 0)              num2,\n" +
            "       (select sum(num1 + num2))                                                                                  attention_num\n" +
            "from user\n" +
            "order by attention_num desc\n" +
            "limit 0,8")
    List<User> attentionTop8();

    /**
     * 查询地区人数
     */
    @Select("select count(*) from user where address like '%${address}%'")
    Integer selectAddressNum(String address);

    /**
     * 查询用户总数
     */
    @Select("select count(*) from user")
    Integer selectUserNum();

    /**
     * 查询所有用户账号
     */
    @Select("select no from user where state = 1")
    List<Integer> getUserNo();

    /**
     * 查询账号状态
     */
    @Select("select state from user where no = #{no}")
    Boolean getUserState(@Param("no") Integer no);

    /**
     * 查询账号当前头像路径
     */
    @Select("select headUrl from user where no = #{no}")
    String getNowHeadUrl(@Param("no") Integer no);
}
