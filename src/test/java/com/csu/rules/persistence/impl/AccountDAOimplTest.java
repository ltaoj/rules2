package com.csu.rules.persistence.impl;

import com.csu.rules.domain.*;
import com.csu.rules.persistence.AccountDAO;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by ltaoj on 17-5-30.
 */
public class AccountDAOimplTest {
    private  AccountDAO accountDAO = new AccountDAOimpl();
    /**
     * 测试获取用户信息
     *
     * @throws Exception
     */
    @Test
    public void testGetUserInfo() throws Exception {
        Account account = new Account();
        account.setStudentId(3903150327l);
        account = accountDAO.getUserInfo(account);
        assertEquals(account.getCollege(), "软件工程");
    }

    @Test
    public void testInsertUserInfoList() throws Exception {
        List<Account> accountList = new ArrayList<Account>();
        accountDAO.insertUserInfoList(accountList);
    }
}