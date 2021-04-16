package pers.jxy.service;

import org.springframework.web.multipart.MultipartFile;
import pers.jxy.entity.AddressUserNum;
import pers.jxy.entity.User;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.TreeSet;

public interface UserService {

    /**
     * 发送验证码，并将验证码存入session,sessionId为email+"-code"
     */
    Boolean sendCode(String email, HttpSession session);

    /**
     * 注册
     */
    Integer[] register(User user);

    /**
     * 登录
     */
    User login(Integer no, String password);

    /**
     * 封禁账号
     */
    Boolean banUser(Integer no, Long times);

    /**
     * 修改密码
     */
    Boolean updatePassword(User user);

    /**
     * 修改用户信息
     */
    Integer updateUser(User user);

    /**
     * 获取作者
     */
    User getAuthor(Integer no);

    /**
     * 查询互相关注
     */
    List<User> selectEachOther(Integer no);

    /**
     * 查询用户关系
     */
    Integer selectUserRelationship(Integer aNo, Integer bNo);

    /**
     * 查询关注我的用户
     */
    List<User> selectCareMe(Integer no);

    /**
     * 查询关注我的用户
     */
    List<User> selectMyCare(Integer no);

    /**
     * 关注他人
     */
    Boolean attention(Integer aNo, Integer bNo);

    /**
     * 关注我的 -> 互相关注
     */
    Boolean goEach(Integer aNo, Integer bNo);

    /**
     * 我关注的 -> 无关系
     */
    Boolean delRelationship(Integer aNo, Integer bNo);

    /**
     * 互相关注 -> 关注我的
     */
    Boolean cancelEach(Integer aNo, Integer bNo);

    /**
     * 查询好友信息
     */
    User getFriendMsg(Integer no);

    /**
     * 查询好友关注列表
     */
    List<User> getFriendAttention(Integer no);

    /**
     * 获取账户笔记本信息
     */
    Integer[] getUserNotebookMsg(Integer no);

    /**
     * 搜索用户
     *
     * @return
     */
    Object[] searchUser(String keyword, Integer no, Integer page);

    Boolean updateHeadUrl(String headUrl, Integer no);

    /**
     * 查询仅七天注册人数
     */
    Object[] selectRegisterNum();

    /**
     * 查询各项前八
     */
    List<List<User>> eachTop8();

    /**
     * 查询地区人数
     */
    TreeSet<AddressUserNum> selectAddressNum();

    /**
     * 查询用户状态
     */
    Boolean getUserState(Integer no);

    /**
     * 上传头像
     */
    String uploadHead(MultipartFile file, Integer no);

    Long checkState(Integer no);
}
