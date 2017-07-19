package com.csu.rules.persistence.impl;

import com.csu.rules.domain.Paperrecord;
import com.csu.rules.exception.PersistenceException;
import com.csu.rules.persistence.PaperrecordDAO;
import com.csu.rules.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ltaoj on 17-7-17.
 */
@Repository
public class PaperrecordDAOimpl implements PaperrecordDAO {
    public Integer insertPaperrecord(Paperrecord paperrecord) throws PersistenceException {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            Integer paperId = (Integer) session.save(paperrecord);
            transaction.commit();
            session.close();
            return paperId;
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    public void deletePaperrecord(int paperId) throws PersistenceException {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            Paperrecord paperrecord = session.get(Paperrecord.class, paperId);
            session.delete(paperrecord);
            transaction.commit();
            session.close();
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    public void deletePaperrecordByStudentId(int studentId) throws PersistenceException {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            String hql = "delete from Paperrecord as paperrecord where studentId=" + studentId;
            session.createQuery(hql).executeUpdate();
            transaction.commit();
            session.close();
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    public void deletePaperrecordByTestId(int testId) throws PersistenceException {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            String hql = "delete from Paperrecord as paperrecord where testId=" + testId;
            session.createQuery(hql).executeUpdate();
            transaction.commit();
            session.close();
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    public void deletePaperrecordByStudentIdAndTestId(int studentId, int testId) throws PersistenceException {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            String hql = "delete from Paperrecord as paperrecord where studentId=" + studentId + " and testId=" + testId;
            session.createQuery(hql).executeUpdate();
            transaction.commit();
            session.close();;
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    public Paperrecord getPaperrecord(int paperId) throws PersistenceException {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            Paperrecord paperrecord = session.get(Paperrecord.class, paperId);
            transaction.commit();
            session.close();
            return paperrecord;
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    public Paperrecord getPaperrecordByStudentIdAndTestId(int studentId, int testId) throws PersistenceException {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            String hql = "from Paperrecord as paperrecord where studentId=" + studentId + " and testId=" + testId;
            List<Paperrecord> list = session.createQuery(hql).list();
            transaction.commit();
            session.close();
            return list != null && list.size() > 0 ? list.get(0) : null;
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    public Paperrecord getPaperrecordByStudentId(int studentId) throws PersistenceException {
        return null;
    }

    public void updatePaperrecord(int paperId, Paperrecord paperrecord) throws PersistenceException {

    }

    public void updatePaperrecordBy(int studentId, int testId, Paperrecord paperrecord) throws PersistenceException {

    }
}
