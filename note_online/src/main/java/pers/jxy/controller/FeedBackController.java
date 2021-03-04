package pers.jxy.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.jxy.entity.Feedback;
import pers.jxy.service.FeedbackService;
import pers.jxy.util.NoteBookOnlineUtils;

/**
 * @author:靳新宇
 * @date : 02-24 15:39
 */
@RestController
public class FeedBackController {
    @Autowired
    FeedbackService feedbackService;

    @PostMapping("/feedback")
    public Boolean feedback(Integer no, Integer type, String body) {
        Feedback feedBack = new Feedback();
        feedBack.setFuNo(no);
        feedBack.setfType(type);
        feedBack.setFBody(body);
        feedBack.setFTime(NoteBookOnlineUtils.getNow());
        return feedbackService.feedback(feedBack);
    }


    @GetMapping("/getFeedbacks")
    public Object[] getFeedbacks(Integer page) {
        return feedbackService.getFeedbacks(page);
    }

    @GetMapping("/updateState")
    public Object[] updateState(Integer no, Integer page) {
        if (feedbackService.updateState(no)) {
            return feedbackService.getFeedbacks(page);
        } else {
            return null;
        }
    }
}
