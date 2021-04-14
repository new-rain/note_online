package pers.jxy.service;

import com.github.pagehelper.PageInfo;
import pers.jxy.entity.Admin;

import java.util.List;

public interface AdminService {
    /**
     * 添加管理员
     */
    Integer addAdmin(Admin admin, String rnum);

    /**
     * 删除管理员
     */
    Boolean delAdmin(Integer id);

    /**
     * 管理员修改密码
     */
    Boolean updatePassword(Integer id, String old, String password);

    /**
     * 查询管理员
     */
    Admin selectAdmin(Integer id, String password);

    /**
     * 生成验证码
     *
     * @param num:生成数量;
     * @return 添加之后的可用注册码
     */
    List<String> addRNum(Integer num);

    /**
     * 获取验证码;
     *
     * @return:查询可用的注册码;
     */
    List<String> getRNum();

    /**
     * 查询所有管理员;
     *
     * @param type:排序类型;
     * @param page:页数
     * @return:返回查询到的管理员;
     */
    public PageInfo<Admin> selectAdmins(Integer type, Integer page);

    /**
     * 添加业绩;
     *
     * @param id:管理员id;
     * @param ach:增加多少业绩;
     * @return:是否添加成功;
     */
    Boolean addAch(Integer id, Integer ach);
}
