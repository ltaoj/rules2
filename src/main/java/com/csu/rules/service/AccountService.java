package com.csu.rules.service;

import com.csu.rules.domain.Account;
import com.csu.rules.domain.Signon;
import com.csu.rules.exception.AccountServiceException;
import org.springframework.stereotype.Service;

/**
 * Created by ltaoj on 17-5-30.
 */
public interface AccountService {
    /**
     * @param studentId
     * @param password
     * @return
     * @throws AccountServiceException
     * 业务层登录异常包括
     * 0.studentId字段检验异常
     * 1.password字段检验异常
     * 2.账号密码错误
     * 3.持久层失败异常
     */
    Account login(long studentId, String password) throws AccountServiceException;

    /**
     * @param signon
     * @return
     * @throws AccountServiceException
     * 4.signon字段检验异常
     */
    Account login(Signon signon) throws AccountServiceException;
}
