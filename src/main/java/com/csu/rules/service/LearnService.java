package com.csu.rules.service;

import com.csu.rules.domain.Title;
import com.csu.rules.exception.LearnServiceException;

import java.util.List;

/**
 * Created by ltaoj on 17-6-10.
 */
public interface LearnService {
    /**
     * @param page
     * @param count
     * @return
     * @throws LearnServiceException
     * 0.page字段不合法
     * 1.count字段不合法
     * 2.没有TitleList返回
     * 3.数据持久化异常
     */
    List<Title> getTitleListByPageAndCount(int page, int count) throws LearnServiceException;
}
