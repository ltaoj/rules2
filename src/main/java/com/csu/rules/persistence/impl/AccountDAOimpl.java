package com.csu.rules.persistence.impl;

import com.csu.rules.domain.Account;
import com.csu.rules.persistence.AccountDAO;
import com.csu.rules.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceException;
import java.util.List;

/**
 * Created by ltaoj on 17-5-30.
 */
@Repository
public class AccountDAOimpl implements AccountDAO{


    public Account login(long studentId, String password) throws PersistenceException {
        try {
            Session session= HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            String hql = "select account from Account as account,  Signon signon " + "where signon.studentId=" + studentId + " and signon.password=" + password;
            List<Account> list = session.createQuery(hql).list();
            transaction.commit();
            session.close();
            return list.size() > 0? list.get(0) : null;
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }
}
