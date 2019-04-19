package com.csu.rules.persistence;

import com.csu.rules.domain.Account;
import com.csu.rules.domain.Signon;
import com.csu.rules.exception.PersistenceException;

import java.util.List;

/**
 * Created by ltaoj on 17-5-30.
 */
public interface AccountDAO {

    /**
     * 获取用户基本信息
     *
     * @param account
     * @return
     * @throws PersistenceException
     */
    Account getUserInfo(Account account) throws PersistenceException;

    /**
     * 插入批量的用户信息
     *
     * @param accountList
     * @throws PersistenceException
     */
    void insertUserInfoList(List<Account> accountList) throws PersistenceException;

    /**
     * 通过注册插入用户信息
     *
     * @param account
     * @throws PersistenceException
     */
    void insertUserInfo(Account account) throws PersistenceException;

    /**
     * 通过权限条件获取学生
     * @param college
     * @return
     * @throws PersistenceException
     */
    List<Account> getAccountListByCondition(String college)throws PersistenceException;

    /**
     * 根据账户Id获取密码信息
     * @param accountId
     * @return
     * @throws PersistenceException
     */
    Signon getSignon(String accountId) throws PersistenceException;

    /**
     * 更新密码信息
     * @param signon
     * @throws PersistenceException
     */
    void updateSignon(Signon signon ) throws PersistenceException;

    /**
     * 更新用户信息
     * @param account
     * @throws PersistenceException
     */
    void updateUser(Account account) throws PersistenceException;
}