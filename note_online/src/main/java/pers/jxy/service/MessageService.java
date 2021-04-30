package pers.jxy.service;

import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Transactional;
import pers.jxy.entity.Message;
@Transactional(readOnly = false)
public interface MessageService {
    Boolean leaveMessage(Message message);

    @Transactional(readOnly = true)
    Message selectByNo(Integer no);

    @Transactional(readOnly = true)
    PageInfo getMessages(Integer no, Integer type, Integer page);

    Boolean readAll(Integer no, Integer type);

    Boolean deleteAll(Integer no, Integer type);

    Boolean checkMsg(Integer no);

    Boolean deleteMsg(Integer no);

    @Transactional(readOnly = true)
    Boolean[] checkHaveNew(Integer no);

    Boolean releaseMsg(String body);
}
