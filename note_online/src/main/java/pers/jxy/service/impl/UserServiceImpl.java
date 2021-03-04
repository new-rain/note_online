package pers.jxy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import pers.jxy.dao.MessageDao;
import pers.jxy.dao.UserDao;
import pers.jxy.entity.AddressUserNum;
import pers.jxy.entity.Message;
import pers.jxy.entity.User;
import pers.jxy.service.UserService;
import pers.jxy.util.NoteBookOnlineUtils;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author:靳新宇
 * @date : 11-05 19:34
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    JavaMailSenderImpl javaMailSender;

    @Autowired
    MessageDao messageDao;

    /**
     * 发送验证码，并将验证码存入session,sessionId为email+"-code"
     */
    @Override
    public Boolean sendCode(String email, HttpSession session) {
        String code = NoteBookOnlineUtils.getRandomStr(4);
        String title = "欢迎注册";
        String content = "欢迎注册在线笔记，您的验证码为：" + code;
        NoteBookOnlineUtils.sendMail(title, content, email, javaMailSender);
        session.setAttribute((email + "-code"), code);
        return Boolean.TRUE;
    }

    /**
     * 注册
     */
    @Override
    public Integer[] register(User user) {
        Integer exist = userDao.selectExist(user.getEmail());
        if (exist != null) {
            return new Integer[]{1, exist};
        } else {
            user.setPassword(NoteBookOnlineUtils.getSHA256Res(user.getPassword()));
            userDao.register(user);
            Integer no = user.getNo();
            return new Integer[]{0, no};
        }
    }

    /**
     * 登录
     */
    @Override
    public User login(Integer no, String password) {
        String pwd = NoteBookOnlineUtils.getSHA256Res(password);
        User user = null;
        user = userDao.login(no, pwd);
        if (user != null) {
            //隐藏密码
            user.setPassword("****************");
        }
        return user;
    }

    @Override
    public Boolean banUser(Integer no) {
        return userDao.banUser(no);
    }


    /**
     * 修改密码
     */
    @Override
    public Boolean updatePassword(User user) {
        user.setPassword(NoteBookOnlineUtils.getSHA256Res(user.getPassword()));
        Integer res = userDao.updatePassword(user);
        return res > 0;
    }

    /**
     * 修改用户信息
     */
    @Override
    public Integer updateUser(User user) {
        Integer res = userDao.updateUser(user);
        return res;
    }

    /**
     * 获取作者信息
     */
    @Override
    public User getAuthor(Integer no) {
        System.out.println(userDao.getAuthor(no));
        return userDao.getAuthor(no);
    }

    @Override
    public List<User> selectEachOther(Integer no) {
        ArrayList<User> users = new ArrayList<>();
        HashSet<Integer> nos = new HashSet<>();
        nos.addAll(userDao.selectEachOther1(no));
        nos.addAll(userDao.selectEachOther2(no));
        for (Integer uNo : nos) {
            users.add(userDao.getUserMsg(uNo));
        }
        return users;
    }

    /**
     * 查询用户之间的关系
     */
    @Override
    public Integer selectUserRelationship(Integer aNo, Integer bNo) {
        return userDao.selectUserRelationship(aNo, bNo);
    }

    @Override
    public List<User> selectCareMe(Integer no) {
        ArrayList<User> users = new ArrayList<>();
        List<Integer> nos = userDao.selectCareMe(no);
        for (Integer uNo : nos) {
            users.add(userDao.getUserMsg(uNo));
        }
        return users;
    }

    @Override
    public List<User> selectMyCare(Integer no) {
        ArrayList<User> users = new ArrayList<>();
        List<Integer> nos = userDao.selectMyCare(no);
        for (Integer uNo : nos) {
            users.add(userDao.getUserMsg(uNo));
        }
        return users;
    }

    @Override
    public Boolean attention(Integer aNo, Integer bNo) {
        //aNo:本人 bNo对方
        //先查询对方是否关注自己
        Integer re = userDao.selectUserRelationship(bNo, aNo);
        //如果对方关注了自己，则将关系改为互相关注，将其改为1
        Boolean res = false;
        if (re > 0) {
            res = userDao.goEach(bNo, aNo);
        } else {
            //如果没有，则新建关系
            res = userDao.addRelationship(aNo, bNo);
        }
        if (res) {
            Message message = new Message();
            message.setMBody("");
            message.setMToWhoNo(bNo);
            message.setMWhoToNo(aNo);
            message.setMType(3);
            messageDao.leaveMessage(message);
        }
        return res;
    }

    @Override
    public Boolean goEach(Integer aNo, Integer bNo) {
        return userDao.goEach(aNo, bNo);
    }

    @Override
    public Boolean delRelationship(Integer aNo, Integer bNo) {
        Integer res = userDao.delRelationship(aNo, bNo);
        return res > 0;
    }

    @Override
    public Boolean cancelEach(Integer aNo, Integer bNo) {
        //aNo为好友账号 、 bNo为本人账号
        Integer no = userDao.selectEachOrder(aNo, bNo);
        if (no != null) {
            Integer res = userDao.cancelEach(aNo, bNo);
            return res > 0;
        } else {
            Integer rNo = userDao.selectEachOrder(bNo, aNo);
            if (rNo > 0) {
                Integer res = userDao.delRelationship(bNo, aNo);
                if (res > 0) {
                    userDao.addRelationship(aNo, bNo);
                    return Boolean.TRUE;
                }
            }
        }
        return Boolean.FALSE;
    }

    @Override
    public User getFriendMsg(Integer no) {
        return userDao.getFriendMsg(no);
    }

    @Override
    public List<User> getFriendAttention(Integer no) {
        ArrayList<User> users = new ArrayList<>();
        HashSet<Integer> nos = new HashSet<>();
        nos.addAll(userDao.selectEachOther1(no));
        nos.addAll(userDao.selectEachOther2(no));
        for (Integer uNo : nos) {
            users.add(userDao.getUserMsg(uNo));
        }
        List<Integer> myCare = userDao.selectMyCare(no);
        for (Integer uNo : myCare) {
            users.add(userDao.getUserMsg(uNo));
        }
        return users;
    }

    @Override
    public Integer[] getUserNotebookMsg(Integer no) {
        Integer[] res = new Integer[5];
        User user = userDao.getUserNoteMsg(no);
        res[0] = user.getNotebookNum();
        res[1] = user.getWatchNum();
        res[2] = user.getGetGoodNum();
        res[3] = user.getGetCommentNum();
        return res;
    }

    @Override
    public LinkedHashSet<User> searchUser(String keyword, Integer no) {
        LinkedHashSet<User> users = new LinkedHashSet<>();
        User user = userDao.searchUserByNo(keyword, no);
        System.out.println(user);
        if (user != null) {
            users.add(user);
        }
        users.addAll(userDao.searchUserByKeyWord(keyword, no));
        users.addAll(userDao.searchUserByKeyWord1(keyword, no));
        users.addAll(userDao.searchUserByKeyWord2(keyword, no));
        return users;
    }

    @Override
    public Boolean updateHeadUrl(String headUrl, Integer no) {
        return userDao.updateHeadUrl(headUrl, no);
    }

    @Override
    public Object[] selectRegisterNum() {
        Object[] res = new Object[2];
        List<String> dates = NoteBookOnlineUtils.get7MdDate();
        ArrayList<Integer> o = new ArrayList<>();
        for (String date : dates) {
            o.add(userDao.selectRegisterNum(date));
        }
        res[0] = o;
        res[1] = dates;
        return res;
    }

    @Override
    public List<List> eachTop8() {
        ArrayList<List> res = new ArrayList<>(3);
        res.add(userDao.readTop8());
        res.add(userDao.goodTop8());
        res.add(userDao.attentionTop8());
        res.add(userDao.getHotUser());
        return res;
    }

    @Override
    public TreeSet<AddressUserNum> selectAddressNum() {
        String[] address = {"河北省", "山西省", "辽宁省", "吉林省", "黑龙江", "江苏省", "浙江省", "安徽省", "福建省", "江西省", "山东省", "河南省", "湖北省", "湖南省", "广东省", "海南省", "四川省", "贵州省", "云南省", "陕西省", "甘肃省", "青海省", "台湾省", "内蒙古", "广西", "西藏", "宁夏", "新疆", "北京市", "天津市", "上海", "重庆市", "香港", "澳门"};
        Integer userNum = userDao.selectUserNum();
        TreeSet<AddressUserNum> res = new TreeSet<>();
        Double othersPro = 0.0;
        Integer otherNum = 0;
        for (String s : address) {
            Integer i = userDao.selectAddressNum(s);
            Double proportation = Double.valueOf(i) / Double.valueOf(userNum);
            if (proportation > 0.01) {
                String pro = NoteBookOnlineUtils.decToPro(proportation);
                if (i > 0) {
                    res.add(new AddressUserNum(s, i, pro));
                }
            } else {
                othersPro += proportation;
                otherNum += i;
            }
        }
        if (othersPro != 0.0) {
            res.add(new AddressUserNum("其他", otherNum, NoteBookOnlineUtils.decToPro(othersPro)));
        }
        return res;
    }
}

