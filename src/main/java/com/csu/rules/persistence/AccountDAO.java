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
}