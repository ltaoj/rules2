package com.csu.rules.service.impl;

import com.csu.rules.domain.Account;
import com.csu.rules.domain.Contestregistion;
import com.csu.rules.domain.Signon;
import com.csu.rules.domain.Testinfo;
import com.csu.rules.exception.AccountServiceException;
import com.csu.rules.persistence.AccountDAO;
import com.csu.rules.persistence.ContestTestDAO;
import com.csu.rules.persistence.SignonDAO;
import com.csu.rules.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by ltaoj on 17-5-30.
 */
@Service
public class AccountServiceimpl implements AccountService {

    private SignonDAO signonDAO;
    private ContestTestDAO contestTestDAO;

    @Autowired
    public AccountServiceimpl(AccountDAO accountDAO, ContestTestDAO contestTestDAO) {
        this.signonDAO = signonDAO;
        this.contestTestDAO = contestTestDAO;
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

    public Account getUserInfo(Account account) throws AccountServiceException {
        AccountServiceException ae = new AccountServiceException();
        if (account == null) {
            ae.setErrorCode(5);
            throw ae;
        }
        return account;
    }

    public Contestregistion registContest(Account account, Testinfo testinfo) {
        ContestTestDAO contestTestDAO = null;
        Contestregistion contestregistion = new Contestregistion();
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        Timestamp startTime = testinfo.getStartTime();
        if (currentTime.before(startTime)) {
            contestTestDAO.registContest(account, testinfo);
        }
        return contestregistion;
    }

    public Contestregistion isRegistedContest(Account account, Testinfo testInfo) {
        Contestregistion contestregistion = new Contestregistion();
        contestregistion = contestTestDAO.isRegistedContest(account, testInfo);
        return contestregistion;
    }

    public Contestregistion changeContestStatus(Contestregistion contestregistion, Testinfo testinfo) {
        Contestregistion contest = new Contestregistion();
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        Timestamp startTime = testinfo.getStartTime();
        Timestamp endTime = testinfo.getEndTime();
        if (currentTime.after(endTime)) {
            contest = contestTestDAO.changeContestStatusEnd(contestregistion);
        }
        if (currentTime.before(endTime) && currentTime.after(startTime)) {
            contest = contestTestDAO.changeContestStatusBegin(contestregistion);
        }
        return contest;
    }

    public List<Testinfo> getContestInfoList() {
        List<Testinfo> contestInfoList = contestTestDAO.getContestInfo();
        return contestInfoList;
    }
}
