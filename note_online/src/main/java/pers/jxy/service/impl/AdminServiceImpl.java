package pers.jxy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.jxy.dao.AdminDao;
import pers.jxy.entity.Admin;
import pers.jxy.service.AdminService;
import pers.jxy.util.NoteBookOnlineUtils;

import java.util.List;
import java.util.Map;

/**
 * @author : 靳新宇
 * @date : 03-27 19:50
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;

    @Override
    public Integer addAdmin(Admin admin, String rnum) {
        Map<String, Integer> ra = adminDao.selectRegisterNum(rnum);
        if (ra != null && ra.get("rnum") != null) {
            if (ra.get("admin") < 0) {
                admin.setPassword(NoteBookOnlineUtils.getSHA256Res(admin.getPassword()));
                //注册码可用，注册用户
                Boolean res = adminDao.addAdmin(admin);
                if (res) {
                    Integer id = admin.getId();
                    //将注册码使用者添加进数据库中
                    Boolean result = adminDao.useRegisterNum(id, rnum);
                    if (result) {
                        //注册成功返回id
                        return id;
                    } else {
                        //注册失败，返回2
                        return 2;
                    }
                } else {
                    //注册失败返回2
                    return 2;
                }

            } else {
                //注册码已使用 返回1
                return 1;
            }
        } else {
            //注册码不存在，返回0
            return 0;
        }
    }

    @Override
    public Boolean delAdmin(Integer id) {
        return adminDao.delAdmin(id);
    }

    @Override
    public Boolean updatePassword(Integer id, String old, String password) {
        Admin admin = adminDao.selectAdmin(id, NoteBookOnlineUtils.getSHA256Res(old));
        if (admin != null) {
            admin.setPassword(NoteBookOnlineUtils.getSHA256Res(password));
            return adminDao.updateAdmin(admin);
        } else {
            return Boolean.FALSE;
        }
    }

    @Override
    public Admin selectAdmin(Integer id, String password) {
        return adminDao.selectAdmin(id, NoteBookOnlineUtils.getSHA256Res(password));
    }

    @Override
    public List<String> getRNum() {
        return adminDao.selectRnums();
    }

    @Override
    public List<String> addRNum(Integer num) {
        for (int i = 0; i < num; i++) {
            String rNum = NoteBookOnlineUtils.getRandomStr(50);
            if (adminDao.selectRegisterNum(rNum) == null) {
                adminDao.addRNum(rNum);
            } else {
                num++;
            }
        }
        return adminDao.selectRnums();
    }


}
