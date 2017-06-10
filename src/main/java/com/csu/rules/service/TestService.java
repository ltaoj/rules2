package com.csu.rules.service;

import com.csu.rules.domain.Account;
import com.csu.rules.domain.Contestregistion;
import com.csu.rules.domain.Testinfo;
import com.csu.rules.domain.Testrecord;
import com.csu.rules.exception.TestServiceException;

import javax.persistence.PersistenceException;
import java.util.List;

/**
 * Created by GF on 2017/6/9.
 */
public interface TestService {
    /**
     * 根据竞赛开始的时间判断是否可以报名竞赛
     * @param account
     * @param testinfo 异常：100.持久化层失败异常
     */
    void registContest(Account account, Testinfo testinfo) throws TestServiceException;

    /**
     * 查看是否已经报名竞赛
     * @param account
     * @param testInfo
     * @return 异常：100.持久化层失败异常
     */
    Contestregistion isRegistedContest(Account account, Testinfo testInfo) throws TestServiceException;

    /**
     * 根据竞赛Id查看已经报名竞赛的人
     * @param testInfo
     * @return 异常:108.list为空 100.持久化层失败异常
     */
    List<Contestregistion> getContestRegistionList(Testinfo testInfo) throws TestServiceException;

    /**
     * 根据竞赛startTime和endTime，依据当前系统时间改变竞赛的状态
     * status:0.未开始 1.开始 2.已结束
     * @param contestregistion
     * @param testinfo
     * @return 异常：100.持久化层失败异常
     */
    Contestregistion changeContestStatus(Contestregistion contestregistion, Testinfo testinfo) throws TestServiceException;

    /**
     * 获取多个竞赛相关信息
     * type 1.竞赛
     * @return 异常：109.contestInfoList为空 100.持久化层失败异常
     */
    List<Testinfo> getContestInfoList() throws TestServiceException;

    /**
     * 获取多个考试相关信息
     * type 0.竞赛
     * @return 异常：110.testInfoList为空 100.持久化层失败异常
     */
    List<Testinfo> getTestInfoList() throws TestServiceException;

    /**
     * 管理员功能：插入考试信息
     * type 0:考试 1:竞赛
     * @param testinfo
     * @throws PersistenceException
     * 异常：100.持久层失败异常
     */
    void insertTestInfo(Testinfo testinfo) throws TestServiceException;

    /**
     * 管理员功能：删除考试信息
     * @param testId
     * @throws PersistenceException
     * 异常：100.持久层失败异常
     */
    void deleteTestInfo(int testId) throws TestServiceException;

    /**
     * 管理员功能：修改考试信息
     * @param testinfo
     * @throws PersistenceException
     * 异常：100.持久层失败异常
     */
    void updateTestInfo(Testinfo testinfo) throws TestServiceException;

    /**
     * 管理员功能：根据考试id查看考试信息
     * @param testId
     * @throws PersistenceException
     * 异常：100.持久层失败异常 111.所查询的testinfo为空
     */
    Testinfo getTestInfo(int testId) throws TestServiceException;

    /**
     * 插入考试成绩
     * @param testrecord
     * @throws com.csu.rules.exception.PersistenceException
     * 异常：100.持久层失败异常
     */
    void insertTestRecord(Testrecord testrecord) throws TestServiceException;

    /**
     * 根据学号和考试id修改考试成绩
     * @param testrecord
     * @throws com.csu.rules.exception.PersistenceException
     * 异常：100.持久层失败异常
     */
    void updateTestRecord(Testrecord testrecord) throws TestServiceException;

    /**
     * 根据学号和考试id查看成绩
     * @param testrecord
     * @return
     * @throws com.csu.rules.exception.PersistenceException
     * 异常：100.持久层失败异常 112.所查看的testrecord为空
     */
    Testrecord getTestRecord(Testrecord testrecord) throws TestServiceException;

    /**
     * 管理员功能：根据考试id查看学生考试成绩,打印成绩表
     * @param testId
     * @return
     * @throws com.csu.rules.exception.PersistenceException
     * 异常：100.持久层失败异常 113.所查看的List<Testrecord>为空
     */
    List<Testrecord> getTestRecordList(int testId) throws TestServiceException;

    /**
     * 用户功能：查看已经参加考试的人数
     * @param testId
     * @return
     * @throws TestServiceException
     * 异常：100.持久层失败异常
     */
    int getTestPersonTotalNum(int testId) throws TestServiceException;
}
