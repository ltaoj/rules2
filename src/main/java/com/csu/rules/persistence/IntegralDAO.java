package com.csu.rules.persistence;

import com.csu.rules.domain.Account;
import com.csu.rules.domain.Integral;
import com.csu.rules.exception.PersistenceException;

/**
 * Created by CMM on 2017/6/10.
 */
public interface IntegralDAO {
    /**
     * 获取用户积分数目
     *
     * @param account
     * @return
     */
    Integral getIntegral(Account account) throws PersistenceException;

    /**
     * 插入积分信息（初始化的时候调用）
     *
     * @param integral
     * @return
     * @throws PersistenceException
     */
    void insertIntegral(Integral integral) throws PersistenceException;

    /**
     * 删除积分信息
     *
     * @param integral
     * @throws PersistenceException
     */
    void updateIntegral(Integral integral) throws PersistenceException;
}
