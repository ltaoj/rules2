package com.csu.rules.persistence.impl;

import com.csu.rules.domain.*;
import com.csu.rules.exception.PersistenceException;
import com.csu.rules.persistence.AccountDAO;
import com.csu.rules.utils.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.List;

/**
 * Created by ltaoj on 17-5-30.
 */
@Repository
public class AccountDAOimpl implements AccountDAO {

    public Account getUserInfo(Account account) throws PersistenceException {
        Session session = HibernateUtil.getSession();
        account = session.get(Account.class, account.getStudentId());
        return account;
    }

    public void insertUserInfoList(List<Account> accountList) throws PersistenceException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        for (Account account : accountList)
            session.save(account);
        transaction.commit();
    }


}