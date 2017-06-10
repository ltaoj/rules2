package com.csu.rules.persistence.impl;

import com.csu.rules.domain.Account;
import com.csu.rules.persistence.AccountDAO;
import com.csu.rules.persistence.SignonDAO;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by CMM on 2017/6/10.
 */
public class SignonDAOimplTest {

    /**
     * 测试登录
     *
     * @throws Exception
     */
    @Test
    public void testLogin() throws Exception {
        SignonDAO signonDAO = new SignonDAOimpl();
        Account account = signonDAO.login(3903150326l, "3903150326");
        System.out.println();
        assertTrue(account != null);
        assertEquals(account.getUsername(), "李涛江");

        account = signonDAO.login(3903150326l, "3903150327");
        assertTrue(account == null);

        account = signonDAO.login(3903150324l, "3903150326");
        assertTrue(account == null);
    }
}
