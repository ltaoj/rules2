package com.csu.rules.persistence.impl;

import com.csu.rules.domain.Account;
import com.csu.rules.domain.Integral;
import com.csu.rules.persistence.AccountDAO;
import com.csu.rules.persistence.IntegralDAO;
import org.junit.Test;

import java.util.Date;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by CMM on 2017/6/10.
 */
public class IntegralDAOimplTest {
    private IntegralDAO integralDAO = new IntegralDAOimpl();

    /**
     * 测试获取积分
     */
    @Test
    public void testGetIntegral() {
        Account account = new Account();
        account.setStudentId(3903150326L);
        Integral integral = integralDAO.getIntegral(account);
        assertEquals(integral.getIntegralNum().intValue(), 10);
    }

    @Test
    public void testInsertIntegral() {
        Integral integral = new Integral();
        integral.setIntegralNum(5);
        integral.setStudentId(3903150327L);
        integralDAO.insertIntegral(integral);
    }

    @Test
    public void testUpdateIntegral() {
        Integral integral = new Integral();
        integral.setIntegralNum(6);
        integral.setStudentId(3903150327L);
        integralDAO.updateIntegral(integral);
    }
}
