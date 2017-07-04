package com.csu.rules.persistence.impl;

import com.csu.rules.domain.Major;
import com.csu.rules.exception.PersistenceException;
import com.csu.rules.persistence.MajorDAO;
import com.csu.rules.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ltaoj on 17-7-4.
 */
@Repository
public class MajorDAOimpl implements MajorDAO {
    public void insertMajor(Major major) throws PersistenceException {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            session.save(major);
            transaction.commit();
            session.close();
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    public List<Major> getMajorListByCollegeId(int collegeId) throws PersistenceException {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            String hql = "from Major as major where collegeId=" + collegeId;
            List<Major> list = session.createQuery(hql).list();
            transaction.commit();
            session.close();
            return list;
        }catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    public List<Major> getMajorListByName(String name) throws PersistenceException {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            String hql = "from Major as major where name=" + name;
            List<Major> list = session.createQuery(hql).list();
            transaction.commit();
            session.close();
            return list;
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }
}
