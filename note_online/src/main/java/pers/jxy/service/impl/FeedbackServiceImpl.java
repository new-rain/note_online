package pers.jxy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.jxy.dao.FeedBackDao;
import pers.jxy.entity.Feedback;
import pers.jxy.entity.Irregularity;
import pers.jxy.service.FeedbackService;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author:靳新宇
 * @date : 02-24 15:37
 */
@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    FeedBackDao feedbackDao;

    @Override
    public Boolean feedback(Feedback feedBack) {
        return feedbackDao.feedback(feedBack);
    }

    @Override
    public Object[] getFeedbacks(Integer page) {
        Object[] o = new Object[2];
        LinkedList<Feedback> feedbacks = new LinkedList<>();
        LinkedList<Feedback> res = new LinkedList<>();
        ArrayList<Feedback> feedbacks1 = feedbackDao.getFeedbacks(0);
        ArrayList<Feedback> feedbacks2 = feedbackDao.getFeedbacks(1);
        feedbacks.addAll(feedbacks1);
        feedbacks.addAll(feedbacks2);
        o[0] = feedbacks.size();
        for (int i = 10 * (page - 1); i < 10 * (page - 1) + 10 && i < feedbacks.size(); i++) {
            res.add(feedbacks.get(i));
        }
        o[1] = res;
        return o;
    }

    @Override
    public Boolean updateState(Integer no) {
        return feedbackDao.updateState(no);
    }


}
