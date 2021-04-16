package pers.jxy.dao;

import org.apache.ibatis.annotations.*;
import pers.jxy.entity.Appeal;

import java.util.List;

/**
 * @author:靳新宇
 * @date : 02-26 19:02
 */
@Mapper
public interface AppealDao {
    @Insert("insert into appeal (au_no, an_no, a_body, a_reason, ad_type, a_time) " +
            "values (#{appeal.auNo},#{appeal.anNo},#{appeal.aBody},#{appeal.aReason},#{appeal.adType},#{appeal.aTime})")
    Boolean insertAppeal(@Param("appeal") Appeal appeal);

    @Select("select a_no,\n" +
            "       au_no,\n" +
            "       an_no,\n" +
            "       (select name from user where no = au_no)     au_name,\n" +
            "       a_body,\n" +
            "       a_reason,\n" +
            "       a_reason,\n" +
            "       ad_type,\n" +
            "       (select n_name from note where n_no = an_no) an_name,\n" +
            "       a_time,\n" +
            "       aa_time,\n" +
            "       a_state,\n" +
            "       a_type\n" +
            "from appeal where a_state = 0")
    List<Appeal> selectAppeals();

    @Select("select a_no,\n" +
            "       au_no,\n" +
            "       an_no,\n" +
            "       (select name from user where no = au_no)     au_name,\n" +
            "       a_body,\n" +
            "       a_reason,\n" +
            "       a_reason,\n" +
            "       ad_type,\n" +
            "       (select n_name from note where n_no = an_no) an_name,\n" +
            "       a_time,\n" +
            "       aa_time,\n" +
            "       a_state,\n" +
            "       a_type\n" +
            "from appeal where a_no = #{ no }")
    Appeal getAppeal(Integer no);

    /**
     * 将申诉状态改为已处理
     */
    @Update("update appeal set a_state = 1 , a_id = #{id} ,a_type = #{aType} where a_no = #{ no }")
    Boolean auditaAppeal(@Param("no") Integer no, @Param("id") Integer id, @Param("aType") Integer aType);

    /**
     * 查看申诉处理记录
     */
    @Select("select (select n_name from note where n_no = an_no) an_name,\n" +
            "       a_reason,\n" +
            "       (select name from user where no = au_no)     au_name,\n" +
            "       (select name from admin where id = a_id)     admin,\n" +
            "       a_type,\n" +
            "       aa_time\n" +
            "from appeal\n" +
            "where a_state = 1\n" +
            "order by aa_time desc\n")
    List<Appeal> selectAppealLog();

    @Select("select (select n_name from note where n_no = an_no) an_name,\n" +
            "       a_reason,\n" +
            "       (select name from user where no = au_no)     au_name,\n" +
            "       a_type,\n" +
            "       aa_time\n" +
            "from appeal\n" +
            "where a_id = #{id}\n" +
            "order by aa_time desc\n")
    List<Appeal> getAppealLog(Integer id);

    @Insert("insert into appeal () values ()")
    Boolean userAppeal(Integer no, Integer iNo);
}
