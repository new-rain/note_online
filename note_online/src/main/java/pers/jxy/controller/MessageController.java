package pers.jxy.controller;

import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.jxy.entity.Message;
import pers.jxy.service.MessageService;

import java.util.ArrayList;

/**
 * @author:靳新宇
 * @date : 01-05 16:13
 */
@RestController
public class MessageController {
    @Autowired
    MessageService messageService;

    @PostMapping("/leaveMessage")
    public Boolean leaveMessage(String mBody, Integer mToWhoNo, Integer mWhoToNo) {
        Message message = new Message();
        message.setMBody(mBody);
        message.setMToWhoNo(mToWhoNo);
        message.setMWhoToNo(mWhoToNo);
        message.setMType(0);
        return messageService.leaveMessage(message);
    }

    @GetMapping("/selectMessageByNo")
    public Message selectByNo(Integer no) {
        return messageService.selectByNo(no);
    }

    @GetMapping("/getMessages")
    public PageInfo getMessages(Integer no, Integer type, Integer page) {
        return messageService.getMessages(no, type, page);
    }

    @PutMapping("/readAll")
    public Boolean readAll(Integer no, Integer type) {
        return messageService.readAll(no, type);
    }

    @PutMapping("/checkMsg")
    public Boolean checkMsg(Integer no) {
        return messageService.checkMsg(no);
    }

    @DeleteMapping("/deleteAll")
    public Boolean deleteAll(Integer no, Integer type) {
        return messageService.deleteAll(no, type);
    }

    @DeleteMapping("/deleteMsg")
    public Boolean deleteMsg(Integer no) {
        return messageService.deleteMsg(no);
    }

    @GetMapping("/checkHaveNew")
    public Boolean[] checkHaveNew(Integer no) {
        return messageService.checkHaveNew(no);
    }

    @PostMapping("/releaseMsg")
    public Boolean releaseMsg(String body) {
        return messageService.releaseMsg(body);
    }
}
