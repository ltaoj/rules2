package com.csu.rules.persistence;

import com.csu.rules.domain.Account;
import com.csu.rules.domain.Signon;
import com.csu.rules.exception.PersistenceException;


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
    Account login(String studentId, String password) throws PersistenceException;

    void insertSignonInfo(Signon signon);
}
