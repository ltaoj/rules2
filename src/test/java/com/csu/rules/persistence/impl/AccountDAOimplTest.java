package com.csu.rules.persistence.impl;

import com.csu.rules.domain.Account;
import com.csu.rules.persistence.AccountDAO;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by ltaoj on 17-5-30.
 */
public class AccountDAOimplTest {

    private AccountDAO accountDAO = new AccountDAOimpl();
    @Test
    public void testLogin() throws Exception {

        Account account = accountDAO.login(3903150326l, "3903150326");
        assertTrue(account != null);
        assertEquals(account.getUsername(), "李涛江");

        account = accountDAO.login(3903150326l, "3903150327");
        assertTrue(account == null);

        account = accountDAO.login(3903150324l, "3903150326");
        assertTrue(account == null);
    }
}
