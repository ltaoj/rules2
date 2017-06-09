package com.csu.rules.persistence.impl;

import com.csu.rules.domain.*;
import com.csu.rules.persistence.AccountDAO;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by ltaoj on 17-5-30.
 */
public class AccountDAOimplTest {

    private AccountDAO accountDAO = new AccountDAOimpl();

    /**
     * 测试登录
     *
     * @throws Exception
     */
    @Test
    public void testLogin() throws Exception {
        Account account = accountDAO.login(3903150326l, "3903150326");
        System.out.println();
        assertTrue(account != null);
        assertEquals(account.getUsername(), "李涛江");

        account = accountDAO.login(3903150326l, "3903150327");
        assertTrue(account == null);

        account = accountDAO.login(3903150324l, "3903150326");
        assertTrue(account == null);
    }

    /**
     * 测试获取用户信息
     *
     * @throws Exception
     */
    @Test
    public void testGetUserInfo() throws Exception {
        Account account = new Account();
        account.setStudentId(3903150327l);
        AccountDAO accountDAO = new AccountDAOimpl();
        account = accountDAO.getUserInfo(account);
        assertEquals(account.getCollege(), "软件工程");
    }

    /**
     * 测试获取错题Id List
     *
     * @throws Exception
     */
    @Test
    public void testGetWrongTitleIdList() throws Exception {
        Account account = new Account();
        account.setStudentId(3903150326L);
        AccountDAO accountDAO = new AccountDAOimpl();
        List<Wrongtitle> wrongtitleList = accountDAO.getWrongTitleIdList(account);
        assertTrue(wrongtitleList.size() == 1);
        assertEquals(wrongtitleList.get(0).getStudentId(), 3903150326L);
    }

    /**
     * 测试获取积分
     */
    @Test
    public void testGetIntegral() {
        Account account = new Account();
        account.setStudentId(3903150326L);
        AccountDAO accountDAO = new AccountDAOimpl();
        Integral integral = accountDAO.getIntegral(account);
        assertEquals(integral.getIntegralNum().intValue(), 10);
    }

    /**
     * 测试获取成绩信息
     */
    @Test
    public void testGetGrade() {
        Account account = new Account();
        account.setStudentId(3903150326L);
        AccountDAO accountDAO = new AccountDAOimpl();
        List<Testrecord> testrecords = accountDAO.getGrades(account);
        assertEquals(testrecords.get(0).getScore().intValue(), 99);
    }

    /**
     * 测试获取打卡信息
     */
    @Test
    public void testGetClockInDayNum() {
        Account account = new Account();
        account.setStudentId(3903150326L);
        AccountDAO accountDAO = new AccountDAOimpl();
        List<Clockin> clockinList = accountDAO.getClockInDayNum(account);
        assertEquals(clockinList.get(0).getClockDay().toString(),"2017-06-07 15:56:00.0");
    }
}
