package com.csu.rules.persistence.impl;

import com.csu.rules.domain.College;
import com.csu.rules.exception.PersistenceException;
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
public class CollegeDAOimpl implements CollegeDAO {
    public void insertCollege(College college) throws PersistenceException {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            session.save(college);
            transaction.commit();
            session.close();
        }catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    public List<College> getCollegeList() throws PersistenceException {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            List<College> list = session.createQuery("from College").list();
            transaction.commit();
            session.close();
            return list;
        }catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }
}
