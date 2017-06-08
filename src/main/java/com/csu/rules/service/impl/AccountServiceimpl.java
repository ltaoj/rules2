package com.csu.rules.service.impl;

import com.csu.rules.domain.Account;
import com.csu.rules.domain.Signon;
import com.csu.rules.exception.AccountServiceException;
import com.csu.rules.persistence.AccountDAO;
import com.csu.rules.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;

/**
 * Created by ltaoj on 17-5-30.
 */
@Service
public class AccountServiceimpl implements AccountService{

    private AccountDAO accountDAO;
    @Autowired
    public AccountServiceimpl(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public Account login(long studentId, String password) throws AccountServiceException {
        try {
            AccountServiceException ae = new AccountServiceException();
            // 字段验证
            if (Long.toString(studentId).length() != 10) {
                ae.setErrorCode(0);
                throw ae;
            } else if (password == null || password.length() < 6) {
                ae.setErrorCode(1);
                throw ae;
            }

            Account account = accountDAO.login(studentId, password);
            if (account == null) {
                ae.setErrorCode(2);
                throw ae;
            }else {
                return account;
            }
        }catch (PersistenceException pe) {
            AccountServiceException ae = new AccountServiceException(pe);
            ae.setErrorCode(3);
            throw ae;
        }
    }

    public Account login(Signon signon) throws AccountServiceException {
        AccountServiceException ae = new AccountServiceException();
        if (signon == null) {
            ae.setErrorCode(4);
            throw ae;
        }
        return login(signon.getStudentId(), signon.getPassword());
    }
}
