package pers.jxy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pers.jxy.entity.AddressUserNum;
import pers.jxy.entity.User;
import pers.jxy.service.NoteBookService;
import pers.jxy.service.UserService;
import pers.jxy.util.NoteBookOnlineUtils;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

/**
 * @author:靳新宇
 * @date : 11-05 15:37
 */
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    NoteBookService noteBookService;

    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 获取生成验证码，将其保存在redis中，并向邮箱发送邮件
     */
    @GetMapping("/getcode")
    public Boolean getcode(String email, HttpSession session) {
        return userService.sendCode(email, session);
    }

    /**
     * 与redis中的验证码进行校验
     */
    @PostMapping("/checkcode")
    public Boolean checkCode(String email, String code) {
        ValueOperations operations = redisTemplate.opsForValue();
        String code1 = String.valueOf(operations.get(email));
        Boolean res = code.equals(code1);
        if (res) {
            redisTemplate.delete(email);
        }
        return res;
    }

    /**
     * 注册新用户
     */
    @PostMapping("/register")
    public Integer[] register(String username, String password, String email) {
        User user = new User();
        user.setNickname(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setHeadUrl("user_default.jpg");
        user.setCreateTime(NoteBookOnlineUtils.getNow());
        Integer[] res = userService.register(user);
        return res;
    }

    /**
     * 登录，返回用户信息
     */
    @PostMapping("/login")
    public User login(Integer no, String password) {
        return userService.login(no, password);
    }

    /**
     * 获取头像
     */
    @GetMapping("/getUserHead")
    public byte[] getUserHead(String headUrl) {
        return NoteBookOnlineUtils.getImg("D:/note/user/" + headUrl);
    }

    /**
     * 封禁账号
     */
    @PutMapping("/banUser")
    public Boolean banUser(Integer no, Long times) {
        return userService.banUser(no, times);
    }

    /**
     * 查看是否封禁。返回封禁时间
     */
    @GetMapping("/checkState")
    public Long checkState(Integer no) {
        return userService.checkState(no);
    }

    /**
     * 修改密码
     */
    @PutMapping("/updatepassword")
    public Boolean updatePassword(Integer no, String password) {
        User user = new User();
        user.setNo(no);
        user.setPassword(password);
        return userService.updatePassword(user);
    }

    /**
     * 修改头像
     */
    @PutMapping("/updateHeadUrl")
    public Boolean updateHeadUrl(String headUrl, Integer no) {
        return userService.updateHeadUrl(headUrl, no);
    }

    /**
     * 上传头像
     */
    @PostMapping("/uploadHead")
    public String uploadHead(MultipartFile file, Integer no) {
        return userService.uploadHead(file, no);
    }

    /**
     * 修改个人信息
     */
    @PutMapping("updateUser")
    public Boolean updateUser(Integer no, String nickname, String brief, String name, String birthday, int gender, String job, String address, String nowAddress) {
        User user = new User();
        user.setNo(no);
        user.setNickname(nickname);
        user.setBrief(brief);
        user.setName(name);
        Date date = NoteBookOnlineUtils.strToDate(birthday);
        java.sql.Date bir = NoteBookOnlineUtils.utilDateToSql(date);
        user.setBirthday(bir);
        user.setGender(gender);
        user.setJob(job);
        user.setAddress(address);
        user.setNowAddress(nowAddress);
        Integer res = userService.updateUser(user);
        if (res > 0) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    /**
     * 获取作者部分信息
     *
     * @param no
     * @return
     */
    @GetMapping("/getAuthor")
    public User getAuthor(Integer no) {
        return userService.getAuthor(no);
    }

    /**
     * 检验密码是否正确
     */
    @GetMapping("/checkOldPassword")
    public Boolean checkOldPassword(Integer no, String password) {
        User user = userService.login(no, password);
        if (user != null) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    /**
     * 关注他人
     */
    @PostMapping("/attention")
    public Boolean attention(Integer aNo, Integer bNo) {
        return userService.attention(aNo, bNo);
    }

    /**
     * 获取互相关注的用户
     */
    @GetMapping("/selectEachOther")
    public List<User> selectEachOther(Integer no) {
        return userService.selectEachOther(no);
    }

    /**
     * 获取关注我的用户
     */
    @GetMapping("/selectCareMe")
    public List<User> selectCareMe(Integer no) {
        return userService.selectCareMe(no);
    }

    /**
     * 获取我关注的用户
     */
    @GetMapping("/selectMyCare")
    public List<User> selectMyCare(Integer no) {
        return userService.selectMyCare(no);
    }

    /**
     * 关注我的 -> 互相关注
     */
    @PutMapping("/goEach")
    public Boolean goEach(Integer aNo, Integer bNo) {
        return userService.goEach(aNo, bNo);
    }

    /**
     * 我关注的 -> 无关系
     */
    @DeleteMapping("/delRelationship")
    Boolean delRelationship(Integer aNo, Integer bNo) {
        return userService.delRelationship(aNo, bNo);
    }

    /**
     * 互相关注 -> 关注我的
     */
    @PostMapping("/cancelEach")
    public Boolean cancelEach(Integer aNo, Integer bNo) {
        return userService.cancelEach(aNo, bNo);
    }

    /**
     * 查询好友信息
     */
    @GetMapping("/getFriendMsg")
    public User getFriendMsg(Integer no) {
        return userService.getFriendMsg(no);
    }

    /**
     * 获取好友关注列表
     */
    @GetMapping("/getFriendAttention")
    public List<User> getFriendAttention(Integer no) {
        return userService.getFriendAttention(no);
    }

    /**
     * 获取账户笔记本信息
     */
    @GetMapping("/getNotebookMsg")
    public Integer[] getAccNotebookMsg(Integer no) {
        Integer[] res = userService.getUserNotebookMsg(no);
        res[4] = noteBookService.selectAllTypes(no).size();
        return res;
    }

    /**
     * 查找用户
     */
    @GetMapping("/searchUser")
    public Object[] searchUser(String keyWord, Integer no, Integer page) {
        return userService.searchUser(keyWord, no, page);
    }

    /**
     * 查询近七天注册人数
     */
    @GetMapping("/selectRegisterNum")
    public Object[] selectRegisterNum() {
        return userService.selectRegisterNum();
    }

    /**
     * 查询各项前8
     */
    @GetMapping("/eachTop")
    public List<List<User>> eachTop8() {
        return userService.eachTop8();
    }

    /**
     * 查询人员分布
     */
    @GetMapping("/addressUserNum")
    TreeSet<AddressUserNum> addressUserNum() {
        return userService.selectAddressNum();
    }

    /**
     * 查询用户状态
     */
    @GetMapping("/getUserState")
    public Boolean getUserState(Integer no) {
        return userService.getUserState(no);
    }

}
