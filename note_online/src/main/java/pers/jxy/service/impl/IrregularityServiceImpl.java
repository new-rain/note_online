package pers.jxy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.jxy.dao.IrregularityDao;
import pers.jxy.dao.MessageDao;
import pers.jxy.entity.Irregularity;
import pers.jxy.entity.Message;
import pers.jxy.service.IrregularityService;
import pers.jxy.util.NoteBookOnlineUtils;

import java.util.List;

/**
 * @author : 靳新宇
 * @date : 02-21 16:47
 */
@Service
public class IrregularityServiceImpl implements IrregularityService {
    @Autowired
    IrregularityDao irregularityDao;

    @Autowired
    MessageDao messageDao;

    @Override
    public Boolean irr(Irregularity irr) {
        return irregularityDao.irr(irr);
    }

    @Override
    public Object[] selectIrrs(Integer page, Integer type) {
        Object[] res = new Object[2];
        PageHelper.startPage(page, 10);
        List<Irregularity> irregularities = irregularityDao.selectIrrs(type);
        PageInfo<Irregularity> pageInfo = new PageInfo<>(irregularities);
        res[0] = irregularityDao.selectIrrNum(type);
        res[1] = irregularities;
        return res;
    }

    @Override
    public Irregularity selectByINo(Integer no) {
        return irregularityDao.selectByINo(no);
    }

    @Override
    public Boolean aduitIrr(Integer inNo, Integer iType, Integer iNo, Integer res, Integer irNo, Integer isNo, String[] msg, Boolean onekey) {
        Boolean res1 = false, res2 = false, res3 = false;
        Message message = new Message();
        message.setMBody(msg[0]);
        message.setMType(4);
        if (onekey) {
            List<Integer> nos = irregularityDao.batchGetNo(inNo, iType);
            //处理相似的记录（笔记账号，举报类型）
            irregularityDao.batchAduit(inNo, iType, res, NoteBookOnlineUtils.getNow());
            //给所有举报者发送信息
            for (Integer no : nos) {
                message.setMToWhoNo(no);
                Boolean bol = messageDao.leaveMessage(message);
                res2 = res2 && bol;
            }
        } else {
            //1.将结果添加到记录中，将违规状态改为已处理
            res1 = irregularityDao.aduit(iNo, res, NoteBookOnlineUtils.getNow());
            //2.想举报者与被举报者发送消息
            message.setMToWhoNo(irNo);
            res2 = messageDao.leaveMessage(message);
        }
        //给被举报者发送信息
        if (msg.length > 1) {
            Message message1 = new Message();
            message1.setMBody(msg[1]);
            message1.setMToWhoNo(isNo);
            message1.setMType(5);
            res3 = messageDao.leaveMessage(message1);
        }
        return res1 && res2 && res3;
    }
}
