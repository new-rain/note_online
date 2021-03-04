package pers.jxy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.jxy.dao.MessageDao;
import pers.jxy.dao.UserDao;
import pers.jxy.entity.Message;
import pers.jxy.service.MessageService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:靳新宇
 * @date : 01-05 16:10
 */
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    MessageDao messageDao;

    @Autowired
    UserDao userDao;

    @Override
    public Boolean leaveMessage(Message message) {
        return messageDao.leaveMessage(message);
    }

    @Override
    public Message selectByNo(Integer no) {
        return messageDao.selectByNo(no);
    }

    @Override
    public PageInfo getMessages(Integer no, Integer type, Integer page) {
        PageHelper.startPage(page, 6);
        ArrayList<Message> messages = null;
        if (type == 0 || type == 1) {
            messages = messageDao.getMessages(no, type);
        } else if (type == 2 || type == 3) {
            PageHelper.startPage(page, 10);
            messages = messageDao.getMessages(no,type);
        } else if (type == 4) {
            messages = messageDao.getMessagesTwo(no);
        }
        PageInfo<Message> pageInfo = new PageInfo<>(messages);
        return pageInfo;
    }

    @Override
    public Boolean readAll(Integer no, Integer type) {
        return messageDao.readAll(no, type) > 0;
    }

    @Override
    public Boolean deleteAll(Integer no, Integer type) {
        return messageDao.deleteAll(no, type) > 0;
    }

    @Override
    public Boolean checkMsg(Integer no) {
        return messageDao.checkMsg(no);
    }

    @Override
    public Boolean deleteMsg(Integer no) {
        return messageDao.deleteMsg(no);
    }

    @Override
    public Boolean[] checkHaveNew(Integer no) {
        Boolean[] res = new Boolean[5];
        for (int i = 0; i < 5; i++) {
            Boolean bol = messageDao.checkHaveNew(no, i) > 0;
            res[i] = bol;
        }
        return res;
    }

    @Override
    public Boolean releaseMsg(String body) {
        List<Integer> nos = userDao.getUserNo();
        Message message = new Message();
        message.setMBody(body);
        message.setMWhoToNo(1);
        message.setMType(4);
        for (Integer no : nos) {
            message.setMToWhoNo(no);
            messageDao.leaveMessage(message);
        }
        return Boolean.TRUE;
    }

}
