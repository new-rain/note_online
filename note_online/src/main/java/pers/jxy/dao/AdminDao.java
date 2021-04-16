package pers.jxy.dao;

import org.apache.ibatis.annotations.*;
import pers.jxy.entity.Admin;
import pers.jxy.util.SQLUtil;

import java.util.List;
import java.util.Map;

@Mapper
public interface AdminDao {

    /**
     * 添加管理员，并返回主键
     */
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into admin (password ,name ,mobile ,email ,headUrl) " +
            "values (#{admin.password},#{admin.name},#{admin.mobile},#{admin.email},#{admin.headUrl})")
    Boolean addAdmin(@Param("admin") Admin admin);

    /**
     * 根据id删除管理员
     */
    @Delete("delete from admin where id = #{id}")
    Boolean delAdmin(@Param("id") Integer id);

    /**
     * 修改管理员信息
     */
    @Update("update admin " +
            "set name=#{admin.name} ,mobile = #{admin.mobile} ," +
            " email = #{admin.email} where id = #{admin.id}")
    Boolean updateAdmin(@Param("admin") Admin admin);

    /**
     * 查询管理员信息
     */
    @Select("select * from admin where id = #{id} and password = #{password}")
    Admin selectAdmin(@Param("id") Integer id, @Param("password") String password);

    /**
     * 添加注册码
     */
    @Insert("insert into registernum (rnum, admin) values (#{rnum}, -1)")
    Boolean addRNum(@Param("rnum") String rnum);

    /**
     * 查询注册码
     */
    @Select("select rnum , admin from registernum where rnum = #{rnum}")
    Map<String, Integer> selectRegisterNum(@Param("rnum") String rnum);

    /**
     * 使用注册码
     */
    @Update("update registernum set admin = #{admin} where rnum = #{rnum}")
    Boolean useRegisterNum(Integer admin, String rnum);

    /**
     * 查询注册码是否已经使用
     */
    @Select("select rnum from registernum where admin = -1")
    List<String> selectRnums();

    /**
     * 查询所有管理员信息（除超级管理员）
     */
    @SelectProvider(value = SQLUtil.class, method = "selectAdminOrder")
    List<Admin> selectAdmins(Integer type);

    /**
     * 修改管理员业绩
     */
    @Update("update admin set achievement = achievement + #{ach} " +
            "where id = #{id}")
    Boolean addAch(@Param("id") Integer id, @Param("ach") Integer ach);

    /**
     * 查询管理员头像保存路径
     */
    @Select("select headUrl from admin where id = #{id}")
    String selectAdminHeadUrl(@Param("id") Integer id);

    /**
     * 修改管理员头像路径
     */
    @Update("update admin set headUrl = #{headUrl} where id = #{id}")
    void updateHeadUrl(String headUrl, Integer id);
}
