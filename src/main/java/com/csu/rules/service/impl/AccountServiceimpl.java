package com.csu.rules.service.impl;

import com.csu.rules.domain.*;
import com.csu.rules.exception.AccountServiceException;
import com.csu.rules.persistence.AccountDAO;
import com.csu.rules.persistence.AdminDAO;
import com.csu.rules.persistence.ContestTestDAO;
import com.csu.rules.persistence.SignonDAO;
import com.csu.rules.persistence.impl.AccountDAOimpl;
import com.csu.rules.persistence.impl.AdminDAOimpl;
import com.csu.rules.persistence.impl.SignonDAOimpl;
import com.csu.rules.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;

/**
 * Created by ltaoj on 17-5-30.
 */
@Service
public class AccountServiceimpl implements AccountService {

    private AccountDAO accountDAO;
    private SignonDAO signonDAO;
    private AdminDAO adminDAO;

    @Autowired
    public AccountServiceimpl(AccountDAO accountDAO, SignonDAO signonDAO, AdminDAO adminDAO) {
        this.accountDAO = accountDAO;
        this.signonDAO = signonDAO;
        this.adminDAO = adminDAO;
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

            Account account = signonDAO.login(studentId, password);
            if (account == null) {
                ae.setErrorCode(2);
                throw ae;
            } else {
                return account;
            }
        } catch (PersistenceException pe) {
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

    public Admin login(Admin admin) throws AccountServiceException {
        AccountServiceException ae = new AccountServiceException();
        if (Integer.toString(admin.getAdminId()).length() == 0) {
            ae.setErrorCode(6);
            throw ae;
        } else if (admin.getPassword() == null) {
            ae.setErrorCode(7);
            throw ae;
        }
        admin = adminDAO.Login(admin);
        if (admin == null) {
            ae.setErrorCode(8);
            throw ae;
        }
        return admin;
    }

    public Account getUserInfo(Account account) throws AccountServiceException {
        AccountServiceException ae = new AccountServiceException();
        if (account == null) {
            ae.setErrorCode(5);
            throw ae;
        }
        return account;
    }

    public void submitProposals(Long studentId, String mail, String content) throws AccountServiceException {

    }

    public void exchange(Integral integral) throws AccountServiceException {
    }

}
