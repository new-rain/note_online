package pers.jxy.service;

import org.springframework.transaction.annotation.Transactional;
import pers.jxy.entity.Feedback;

/**
 * @author : 靳新宇
 * @date : 02-24 15:32
 */
@Transactional(readOnly = false)
public interface FeedbackService {

    @Transactional(readOnly = false)
    Boolean feedback(Feedback feedBack);

    Object[] getFeedbacks(Integer page);

    @Transactional(readOnly = false)
    Boolean updateState(Integer no);
}
