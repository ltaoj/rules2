package com.csu.rules.service;

import com.csu.rules.domain.*;
import com.csu.rules.exception.LearnServiceException;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

/**
 * Created by ltaoj on 17-6-10.
 */
public interface LearnService {
    /**
     * 试题练习获取试题
     * @param page
     * @param count
     * @return
     * @throws LearnServiceException
     * 51.page字段不合法
     * 52.count字段不合法
     * 50.数据持久化异常
     */
    List<Title> getTitleListByPage(int page, int count) throws LearnServiceException;

    /**
     * 考试模块随机试题
     * @param count
     * @return
     * @throws LearnServiceException
     * 52.count字段不合法
     * 50.数据持久化异常
     */
    List<Title> getTitleListByRandom(int count) throws LearnServiceException;

    /**
     * 试题练习模块提交试题
     * @param titleList
     * @return
     * @throws LearnServiceException
     * 53.titleList字段不合法
     * 50.数据持久化异常
     *
     */
    List<Title> submitTitleList(Account account, List<Title> titleList) throws LearnServiceException;

    /**
     * 题目是否正确,正确时将options设为null，错误返回正确题目
     * @param title
     * @return
     * @throws LearnServiceException
     * 54.title字段合法性
     * 55.题目正确性检查异常
     * 50.数据持久化异常
     */
    Title getCorrectTitle(Title title) throws LearnServiceException;

    /**
     * @param title
     * @return
     * @throws LearnServiceException
     * 54.title字段合法性
     * 55.题目正确性检查
     * 50.数据持久化异常
     */
    boolean isTitleCorrect(Title title) throws LearnServiceException;
    /**
     * 得到用户错题列表
     * @param account
     * @return
     * @throws LearnServiceException
     * 56.account字段不合法
     * 50.数据持久化异常
     *
     */
    List<Wrongtitle> getWrongTitleListByStudentId(Account account) throws LearnServiceException;

    /**
     * 竞赛获取试题以及获取错误试题
     * @param integers
     * @return
     * @throws LearnServiceException
     * 57.integers字段不合法
     * 50.数据持久化异常
     */
    List<Title> getTitleListByTitleIds(Set<Integer> integers) throws LearnServiceException;

    /**
     * 获取单个试题
     * @param title
     * @return
     * @throws LearnServiceException
     * 58.title字段不合法
     * 59.没有Title返回
     * 50.数据持久化异常
     */
    Title getTitle(Title title) throws LearnServiceException;

    /**
     * @param titleList
     * @return 分数
     * @throws LearnServiceException
     * 60.titleList字段不合法
     * 50.数据持久化异常
     */
    int getTitlePageScore(List<Title> titleList) throws LearnServiceException;

    /**
     * @param clockin
     * @throws LearnServiceException
     * 61.clockin字段不合法
     * 50.数据持久化异常
     */
    void insertClock(Clockin clockin) throws LearnServiceException;

    /**
     * @return
     * @throws LearnServiceException
     * 56.account字段不合法
     * 50.数据持久化异常
     */
    List<Clockin> getAllClocks(Account account) throws LearnServiceException;

    /**
     * @param account
     * @param datetime
     * @return
     * @throws LearnServiceException
     * 56.account字段不合法
     * 62.datetime字段不合法
     * 50.数据持久化异常
     */
    Clockin getClockDetailByDay(Account account, Timestamp datetime) throws LearnServiceException;

    /**
     * 更新今天的打卡信息
     * @param clockin
     * @throws LearnServiceException
     * 61.clockin字段不合法或者不是当前日期
     * 50.数据持久化异常
     */
    void updateTodayClock(Clockin clockin) throws LearnServiceException;
}
