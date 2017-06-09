package com.csu.rules.persistence.impl;

import com.csu.rules.domain.*;
import com.csu.rules.persistence.AccountDAO;
import com.csu.rules.utils.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceException;
import java.util.List;

/**
 * Created by ltaoj on 17-5-30.
 */
@Repository
public class AccountDAOimpl implements AccountDAO {

    public Account login(long studentId, String password) throws PersistenceException {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            String hql = "select account from Account as account,  Signon signon " + "where signon.studentId=" + studentId + " and signon.password=" + password;
            List<Account> list = session.createQuery(hql).list();
            transaction.commit();
            session.close();
            return list.size() > 0 ? list.get(0) : null;
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    public Account getUserInfo(Account account) throws PersistenceException {
        Session session = HibernateUtil.getSession();
        account = session.get(Account.class, account.getStudentId());
        return account;
    }

    public List getWrongTitleIdList(Account account) throws PersistenceException {
        Session session = HibernateUtil.getSession();
        Criteria criteria = session.createCriteria(Wrongtitle.class);
        criteria.add(Restrictions.eq("studentId", account.getStudentId()));
        List<Wrongtitle> wrongtitleList = criteria.list();
        return wrongtitleList;
    }

    public Integral getIntegral(Account account) throws PersistenceException {
        Integral integral = new Integral();
        Session session = HibernateUtil.getSession();
        Criteria criteria = session.createCriteria(Integral.class);
        criteria.add(Restrictions.eq("studentId", account.getStudentId()));
        integral = (Integral) criteria.list().get(0);
        return integral;
    }

    public List<Testrecord> getGrades(Account account) throws PersistenceException {
        List<Testrecord> testrecordList = null;
        Session session = HibernateUtil.getSession();
        Criteria criteria = session.createCriteria(Testrecord.class);
        criteria.add(Restrictions.eq("studentId", account.getStudentId()));
        testrecordList = criteria.list();

        return testrecordList;
    }


    public List<Clockin> getClockInDayNum(Account account) throws PersistenceException {
        List<Clockin> clockinList = null;
        Session session = HibernateUtil.getSession();
        Criteria criteria = session.createCriteria(Clockin.class);
        criteria.add(Restrictions.eq("studentId", account.getStudentId()));
        clockinList = criteria.list();
        return clockinList;
    }

}