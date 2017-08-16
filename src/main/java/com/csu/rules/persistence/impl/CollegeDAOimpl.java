package com.csu.rules.persistence.impl;

import com.csu.rules.domain.College;
import com.csu.rules.exception.PersistenceException;
import com.csu.rules.persistence.AbstractDAO;
import com.csu.rules.persistence.CollegeDAO;
import com.csu.rules.utils.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ltaoj on 17-7-4.
 */
@Repository
public class CollegeDAOimpl extends AbstractDAO implements CollegeDAO {
    public void insertCollege(College college) throws PersistenceException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = getTransation(session);
        try {
            session.save(college);
            session.flush();
            transaction.commit();
        }catch (RuntimeException e) {
            transaction.rollback();
            throw new PersistenceException(e);
        }finally {
            session.close();
        }
    }

    public List<College> getCollegeList() throws PersistenceException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = getTransation(session);
        try {
            List<College> list = session.createQuery("from College").list();
            session.flush();
            transaction.commit();
            return list;
        }catch (RuntimeException e) {
            transaction.rollback();
            throw new PersistenceException(e);
        }finally {
            session.close();
        }
    }
}
