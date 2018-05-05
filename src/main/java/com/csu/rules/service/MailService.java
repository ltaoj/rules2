package com.csu.rules.service;

import com.csu.rules.domain.Feedback;

/**
 * Created by ltaoj on 2018/05/05 17:55.
 *
 * @version : 1.0
 */
public interface MailService {

    /**
     * 收到反馈信息自动回复邮件
     * @param feedback
     */
    void autoReply(Feedback feedback);

    /**
     * 将反馈内容转发到内部人员邮箱
     * @param feedback
     */
    void forwardFeedback(Feedback feedback);
}
