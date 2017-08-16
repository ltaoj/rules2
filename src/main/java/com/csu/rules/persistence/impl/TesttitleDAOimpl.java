package com.csu.rules.persistence.impl;

import com.csu.rules.domain.Account;
import com.csu.rules.domain.Testinfo;
import com.csu.rules.domain.Testtitle;
import com.csu.rules.exception.PersistenceException;
import com.csu.rules.persistence.AbstractDAO;
import com.csu.rules.persistence.TesttitleDAO;
import com.csu.rules.utils.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ltaoj on 17-7-2.
 */
@Repository
public class TesttitleDAOimpl extends AbstractDAO implements TesttitleDAO {
    public void insertTesttitle(Testtitle testtitle) throws PersistenceException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = getTransation(session);
        try {
            session.save(testtitle);
            session.flush();
            transaction.commit();
        }catch (RuntimeException e) {
            transaction.rollback();
            throw new PersistenceException(e);
        } finally {
            session.close();
        }
    }

    public Testtitle getTesttitle(long studentId, int testId) throws PersistenceException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = getTransation(session);
        try {
            String hql = "from Testtitle as testtitle where studentId=" + studentId + " and testId=" + testId;
            List<Testtitle> list = session.createQuery(hql).list();
            session.flush();
            transaction.commit();
            return list != null && list.size() > 0 ? list.get(0) : null;
        }catch (RuntimeException e) {
            transaction.rollback();
            throw new PersistenceException(e);
        } finally {
            session.close();
        }
    }

    public Testtitle getTesttitle(Testtitle testtitle) throws PersistenceException {
        return getTesttitle(testtitle.getStudentId(), testtitle.getTestId());
    }

    public Testtitle getTesttitle(Account account, Testinfo testinfo) throws PersistenceException {
        return getTesttitle(account.getStudentId(), testinfo.getTestId());
    }

    public void deleteTesttitle(long studentId, int testId) throws PersistenceException {
        Testtitle testtitle = new Testtitle();
        testtitle.setStudentId(studentId);
        testtitle.setTestId(testId);
        deleteTesttitle(testtitle);
    }

    public void deleteTesttitle(Testtitle testtitle) throws PersistenceException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = getTransation(session);
        try {
            session.delete(testtitle);
            session.flush();
            transaction.commit();
        }catch (RuntimeException e) {
            transaction.rollback();
            throw new PersistenceException(e);
        } finally {
            session.close();
        }
    }

    public void deleteTesttitle(Account account, Testinfo testinfo) throws PersistenceException {
        deleteTesttitle(account.getStudentId(), testinfo.getTestId());
    }

    public List<Testtitle> getTesttitleList(int testId) throws PersistenceException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = getTransation(session);
        try {
            String hql = "from Testtitle as testtitle where testId=" + testId;
            List<Testtitle> list = session.createQuery(hql).list();
            session.flush();
            transaction.commit();
            return list != null && list.size() > 0 ? list : null;
        }catch (RuntimeException e) {
            transaction.rollback();
            throw new PersistenceException(e);
        } finally {
            session.close();
        }
    }
}
