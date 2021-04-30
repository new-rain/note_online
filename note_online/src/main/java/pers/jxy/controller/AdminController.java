package pers.jxy.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pers.jxy.entity.Admin;
import pers.jxy.service.AdminService;
import pers.jxy.util.NoteBookOnlineUtils;

import java.util.List;

/**
 * @author : 靳新宇
 * @date : 03-27 19:52
 */
@RestController
public class AdminController {
    @Autowired
    AdminService adminService;

    @GetMapping("/adminLogin")
    public Admin AdminLogin(Integer id, String password) {
        return adminService.selectAdmin(id, password);
    }

    @PutMapping("/updatePassword")
    public Boolean updatePassword(Integer id, String old, String password) {
        return adminService.updatePassword(id, old, password);
    }

    @PostMapping("/adminRegister")
    public Integer adminRegister(Admin admin, String rnum) {
        admin.setHeadUrl("admin_default.jpg");
        return adminService.addAdmin(admin, rnum);
    }

    /**
     * 获取头像
     */
    @GetMapping("/getAdminHead")
    public byte[] getAdminHead(String headUrl) {
        return NoteBookOnlineUtils.getImg("D:/note/admin/" + headUrl);
    }

    @PutMapping("/updateAdmin")
    public Boolean updateAdmin(Admin admin) {
        return adminService.updateAdmin(admin);
    }

    /**
     * 上传头像
     */
    @PostMapping("/uploadAdminHead")
    public String uploadAdminHead(MultipartFile file, Integer id) {
        return adminService.uploadHead(file, id);
    }

    @GetMapping("/getRNum")
    public List<String> getRNum() {
        return adminService.getRNum();
    }

    @PostMapping("/addRNum")
    public List<String> addRNum(Integer num) {
        return adminService.addRNum(num);
    }

    @GetMapping("/selectAdmins")
    public PageInfo<Admin> selectAdmins(Integer type, Integer page) {
        return adminService.selectAdmins(type, page);
    }

    @DeleteMapping("/delAdmin")
    public Boolean delAdmin(Integer id) {
        return adminService.delAdmin(id);
    }

    @PutMapping("/addAch")
    public Boolean addAch(Integer id, Integer ach) {
        return adminService.addAch(id, ach);
    }
}
