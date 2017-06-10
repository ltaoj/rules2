package com.csu.rules.persistence;

import com.csu.rules.domain.Account;

import javax.persistence.PersistenceException;

/**
 * Created by CMM on 2017/6/10.
 */
public interface SignonDAO {
    /**
     * 登录
     *
     * @param studentId
     * @param password
     * @return
     * @throws PersistenceException
     */
    Account login(long studentId, String password) throws PersistenceException;
}
