package com.csu.rules.service;

import com.csu.rules.domain.Account;
import com.csu.rules.domain.Title;
import com.csu.rules.domain.Wrongtitle;
import com.csu.rules.exception.LearnServiceException;
import com.csu.rules.exception.TitleServiceException;

import java.util.List;
import java.util.Set;

/**
 * Created by ltaoj on 17-6-11.
 */
public interface TitleService {
    /**
     * 试题练习获取试题
     * @param page
     * @param count
     * @return
     * @throws TitleServiceException
     * 51.page字段不合法
     * 52.count字段不合法
     * 50.数据持久化异常
     */
    List<Title> getTitleListByPage(int page, int count) throws TitleServiceException;

    /**
     * 考试模块随机试题
     * @param count
     * @return
     * @throws TitleServiceException
     * 52.count字段不合法
     * 50.数据持久化异常
     */
    List<Title> getTitleListByRandom(int count) throws TitleServiceException;

    /**
     * 试题练习模块提交试题
     * @param titleList
     * @return
     * @throws TitleServiceException
     * 53.titleList字段不合法
     * 50.数据持久化异常
     *
     */
    List<Title> submitTitleList(Account account, List<Title> titleList) throws TitleServiceException;

    /**
     * 题目是否正确,正确时将options设为null，错误返回正确题目
     * @param title
     * @return
     * @throws TitleServiceException
     * 54.title字段合法性
     * 55.题目正确性检查异常
     * 50.数据持久化异常
     */
    Title getCorrectTitle(Title title) throws TitleServiceException;

    /**
     * @param title
     * @return
     * @throws TitleServiceException
     * 54.title字段合法性
     * 55.题目正确性检查
     * 50.数据持久化异常
     */
    boolean isTitleCorrect(Title title) throws TitleServiceException;
    /**
     * 得到用户错题列表
     * @param account
     * @return
     * @throws TitleServiceException
     * 56.account字段不合法
     * 50.数据持久化异常
     *
     */
    List<Wrongtitle> getWrongTitleListByStudentId(Account account) throws TitleServiceException;

    /**
     * 竞赛获取试题以及获取错误试题
     * @param integers
     * @return
     * @throws TitleServiceException
     * 57.integers字段不合法
     * 50.数据持久化异常
     */
    List<Title> getTitleListByTitleIds(Set<Integer> integers) throws TitleServiceException;

    /**
     * 获取单个试题
     * @param title
     * @return
     * @throws TitleServiceException
     * 58.title字段不合法
     * 59.没有Title返回
     * 50.数据持久化异常
     */
    Title getTitle(Title title) throws TitleServiceException;

    /**
     * @param titleList
     * @return 分数
     * @throws TitleServiceException
     * 60.titleList字段不合法
     * 50.数据持久化异常
     */
    int getTitlePageScore(List<Title> titleList) throws TitleServiceException;
}
