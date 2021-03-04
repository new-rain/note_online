package pers.jxy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.jxy.dao.AppealDao;
import pers.jxy.dao.MessageDao;
import pers.jxy.dao.NoteDao;
import pers.jxy.entity.Appeal;
import pers.jxy.entity.Message;
import pers.jxy.service.AppealService;

import java.util.List;

/**
 * @author:靳新宇
 * @date : 02-26 19:08
 */
@Service
public class AppealServiceImpl implements AppealService {
    @Autowired
    AppealDao appealDao;

    @Autowired
    MessageDao messageDao;

    @Autowired
    NoteDao noteDao;

    @Override
    public Boolean insertAppeal(Appeal appeal) {
        return appealDao.insertAppeal(appeal);
    }

    @Override
    public PageInfo selectAppeals(Integer page) {
        PageHelper.startPage(page, 10);
        List<Appeal> appeals = appealDao.selectAppeals();
        PageInfo<Appeal> appeal = new PageInfo<>(appeals);
        return appeal;
    }

    @Override
    public Appeal getAppeal(Integer no) {
        return appealDao.getAppeal(no);
    }

    @Override
    public Boolean auditaAppeal(Integer no) {
        return appealDao.auditaAppeal(no);
    }

    @Override
    public Boolean appealSuccess(Integer adType, Integer uno, Integer ano, String reason, String noteName, Integer noteNo) {
        //如果原处理是1，表示要求修改，则发送消息,说明申诉成功
        Message message = new Message();
        message.setMToWhoNo(uno);
        message.setMType(4);
        message.setMBody("您申诉的关于《" + noteName + "》" + reason + "，经审核，申诉成功，原处理以取消");
        //如果原处理是2，将笔记状态改为正常
        if (adType == 2) {
            noteDao.delNote(noteNo, 1);
        }
        messageDao.leaveMessage(message);
        //将投诉取消

        return appealDao.auditaAppeal(ano);
    }
}
