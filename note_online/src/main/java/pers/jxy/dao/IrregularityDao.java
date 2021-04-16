package pers.jxy.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import pers.jxy.entity.Irregularity;

import java.util.List;

public interface IrregularityDao {
    /**
     * 举报
     */
    @Insert("insert into irregularity (i_type, i_body, ir_no, is_no, in_no, in_name, i_time) " +
            "values (#{irr.iType},#{irr.iBody},#{irr.irNo},#{irr.isNo},#{irr.inNo},#{irr.inName},#{irr.iTime})")
    Boolean irr(@Param("irr") Irregularity irr);

    /**
     * 查询待处理违规记录
     */
    @Select("select i_no,\n" +
            "       (select n_name from note where n_no = i_no)  in_name,\n" +
            "       i_type,\n" +
            "       i_body,\n" +
            "       (select nickname from user where no = ir_no) ir_name\n" +
            "from irregularity\n" +
            "where i_state = 0\n" +
            "order by i_time desc")
    List<Irregularity> selectIrrs();

    /**
     * 查看处理记录
     */
    @Select("select (select n_name from note where n_no = i_no) in_name,\n" +
            "       i_type,\n" +
            "       i_body,\n" +
            "       i_result,\n" +
            "       (select name from admin where id = a_id)   admin,\n" +
            "       ia_time\n" +
            "from irregularity\n" +
            "where i_state = 1 order by ia_time desc")
    List<Irregularity> selctAuditLog();

    /**
     * 查看管理员处理记录
     */
    @Select("select (select n_name from note where n_no = i_no) in_name, " +
            "       i_type, " +
            "       i_body, " +
            "       i_result, " +
            "       ia_time " +
            "from irregularity " +
            "where a_id = #{id} " +
            "order by ia_time desc")
    List<Irregularity> getAuditLog(@Param("id") Integer id);

    /**
     * 查询待处理数量
     */
    @Select("select count(*) from irregularity where i_state=#{type}")
    Integer selectIrrNum(@Param("type") Integer type);

    /**
     * 根据序号查询违规记录
     */
    @Select("select i_no,\n" +
            "       i_type,\n" +
            "       i_body,\n" +
            "       ir_no,\n" +
            "       (select nickname from user where no = ir_no) ir_name,\n" +
            "       is_no,\n" +
            "       (select nickname from user where no = is_no) is_name,\n" +
            "       in_no,\n" +
            "       in_name,\n" +
            "       i_state,\n" +
            "       i_time\n" +
            "from irregularity\n" +
            "where i_no =  #{no}")
    Irregularity selectByINo(@Param("no") Integer no);

    @Update("update irregularity set i_state=1, i_result=#{res}, " +
            "ia_time=#{time} ,a_id = #{id} " +
            "where i_no = #{iNo}")
    Boolean aduit(@Param("iNo") Integer iNo, @Param("res") Integer res, @Param("time") String time, @Param("id") Integer id);

    @Update("update irregularity set i_state=1, i_result=#{res}, ia_time=#{time} ,a_id = #{id} where in_no = #{inNo} and i_type = #{iType}")
    Boolean batchAduit(@Param("inNo") Integer inNo, @Param("iType") Integer iType, @Param("res") Integer res, @Param("time") String time);

    /**
     * 获取相似记录的举报者账号
     */
    @Select("select ir_no from irregularity where in_no = #{inNo} and i_type = #{iType} and i_state = 0")
    List<Integer> batchGetNo(@Param("inNo") Integer inNo, @Param("iType") Integer iType);

    /**
     * 查询账号封禁原因
     */
    @Select("select in_no,\n" +
            "       (select n_name from note where n_no = in_no) in_name,\n" +
            "       i_type,\n" +
            "       i_body\n" +
            "from irregularity\n" +
            "where i_result = 3\n" +
            "  and ir_no = #{no}\n" +
            "order by ia_time desc")
    List<Irregularity> getBanReason(@Param("no") Integer no);
}
