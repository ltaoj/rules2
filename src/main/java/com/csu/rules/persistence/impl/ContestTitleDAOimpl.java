package com.csu.rules.persistence.impl;

import com.csu.rules.domain.Contesttitle;
import com.csu.rules.domain.Testinfo;
import com.csu.rules.exception.PersistenceException;
import com.csu.rules.persistence.ContestTitleDAO;
import com.csu.rules.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

/**
 * Created by ltaoj on 17-7-2.
 */
@Repository
public class ContestTitleDAOimpl implements ContestTitleDAO {

    public void insertContesttitle(Contesttitle contesttitle) throws PersistenceException {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
        try {
            session.save(contesttitle);
            session.flush();
            transaction.commit();
        }catch (RuntimeException e) {
            transaction.rollback();
            throw new PersistenceException(e);
        }finally {
            session.close();
        }
    }

    public Contesttitle getContesttitle(int testId) throws PersistenceException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Contesttitle contesttitle = session.get(Contesttitle.class, testId);
            session.flush();
            transaction.commit();
            return contesttitle;
        }catch (RuntimeException e) {
            transaction.rollback();
            throw new PersistenceException(e);
        }finally {
            session.close();
        }
    }

    public Contesttitle getContesttitle(Testinfo testinfo) throws PersistenceException {
        return getContesttitle(testinfo.getTestId());
    }

    public Contesttitle getContesttitle(Contesttitle contesttitle) throws PersistenceException {
        return getContesttitle(contesttitle.getTestId());
    }

    public void deleteContesttitle(int testId) throws PersistenceException {
        Contesttitle contesttitle = new Contesttitle();
        contesttitle.setTestId(testId);
        deleteContesttitle(contesttitle);
    }

    public void deleteContesttitle(Testinfo testinfo) throws PersistenceException {
        deleteContesttitle(testinfo.getTestId());
    }

    public void deleteContesttitle(Contesttitle contesttitle) throws PersistenceException {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
        try {
            session.delete(contesttitle);
            session.flush();
            transaction.commit();
        }catch (RuntimeException e) {
            transaction.rollback();
            throw new PersistenceException(e);
        }finally {
            session.close();
        }
    }
}
