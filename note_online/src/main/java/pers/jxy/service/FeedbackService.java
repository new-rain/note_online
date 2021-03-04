package pers.jxy.service;

import com.github.pagehelper.PageInfo;
import pers.jxy.entity.Feedback;

/**
 * @author : 靳新宇
 * @date : 02-24 15:32
 */
public interface FeedbackService {

    Boolean feedback(Feedback feedBack);

    Object[] getFeedbacks(Integer page);

    Boolean updateState(Integer no);
}
