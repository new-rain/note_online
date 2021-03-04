package pers.jxy.service;

import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import pers.jxy.entity.Message;

public interface MessageService {
    Boolean leaveMessage(Message message);

    Message selectByNo(Integer no);

    PageInfo getMessages(Integer no, Integer type, Integer page);

    Boolean readAll(Integer no, Integer type);

    Boolean deleteAll(Integer no, Integer type);

    Boolean checkMsg(Integer no);

    Boolean deleteMsg(Integer no);

    Boolean[] checkHaveNew(Integer no);

    Boolean releaseMsg(String body);
}
