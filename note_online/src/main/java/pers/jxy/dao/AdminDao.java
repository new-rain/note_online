package pers.jxy.dao;

import org.apache.ibatis.annotations.*;
import pers.jxy.entity.Admin;

import java.util.List;
import java.util.Map;

@Mapper
public interface AdminDao {

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into admin (password ,name ,mobile ,email) " +
            "values (#{admin.password},#{admin.name},#{admin.mobile},#{admin.email})")
    Boolean addAdmin(@Param("admin") Admin admin);

    @Delete("delete from admin where id = #{id}")
    Boolean delAdmin(@Param("id") Integer id);

    @Update("update admin " +
            "set password=#{admin.password}, name=#{admin.name} , mobile = #{admin.mobile} , email = #{admin.email} " +
            "where id = #{admin.id}")
    Boolean updateAdmin(@Param("admin") Admin admin);

    @Select("select * from admin where id = #{id} and password = #{password}")
    Admin selectAdmin(@Param("id") Integer id, @Param("password") String password);

    @Insert("insert into registernum (rnum, admin) values (#{rnum}, -1)")
    Boolean addRNum(@Param("rnum") String rnum);

    @Select("select rnum , admin from registernum where rnum = #{rnum}")
    Map<String, Integer> selectRegisterNum(@Param("rnum") String rnum);

    @Update("update registernum set admin = #{admin} where rnum = #{rnum}")
    Boolean useRegisterNum(Integer admin, String rnum);

    @Select("select rnum from registernum where admin = -1")
    List<String> selectRnums();
}
