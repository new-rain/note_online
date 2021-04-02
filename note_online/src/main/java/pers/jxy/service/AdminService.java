package pers.jxy.service;

import pers.jxy.entity.Admin;

import java.util.List;

public interface AdminService {
    /**
     * 添加管理员
     */
    Integer addAdmin(Admin admin,String rnum);

    /**
     * 删除管理员
     */
    Boolean delAdmin(Integer id);

    /**
     * 管理员修改密码
     */
    Boolean updatePassword(Integer id,String old, String password);

    /**
     * 查询管理员
     */
    Admin selectAdmin(Integer id, String password);

    /**
     * 生成验证码
     */
    List<String> addRNum(Integer num);

    /**
     * 获取验证码
     */
    List<String> getRNum();
}
