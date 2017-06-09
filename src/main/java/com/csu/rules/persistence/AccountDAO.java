package com.csu.rules.persistence;

import com.csu.rules.domain.Account;
import com.csu.rules.domain.Clockin;
import com.csu.rules.domain.Integral;
import com.csu.rules.domain.Testrecord;

import javax.persistence.PersistenceException;
import java.util.List;

/**
 * Created by ltaoj on 17-5-30.
 */
public interface AccountDAO {
    /**
     * 登录
     *
     * @param studentId
     * @param password
     * @return
     * @throws PersistenceException
     */
    Account login(long studentId, String password) throws PersistenceException;

    /**
     * 获取用户基本信息
     *
     * @param account
     * @return
     * @throws PersistenceException
     */
    Account getUserInfo(Account account) throws PersistenceException;

    /**
     * 获取错题
     *
     * @param account
     * @return
     * @throws PersistenceException
     */
    List getWrongTitleIdList(Account account) throws PersistenceException;

    /**
     * 获取用户积分
     *
     * @param account
     * @return
     * @throws PersistenceException
     */
    Integral getIntegral(Account account) throws PersistenceException;

    /**
     * 获取用户成绩
     *
     * @param account
     * @return
     * @throws PersistenceException
     */
    List<Testrecord> getGrades(Account account) throws PersistenceException;

    /**
     * 获取打卡List
     * @return
     * @throws PersistenceException
     */
    List<Clockin> getClockInDayNum(Account account) throws PersistenceException;

}