package com.csu.rules.persistence.impl;

import com.csu.rules.domain.Clazz;
import com.csu.rules.exception.PersistenceException;
import com.csu.rules.persistence.ClazzDAO;
import com.csu.rules.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ltaoj on 17-7-4.
 */
@Repository
public class ClazzDAOimpl implements ClazzDAO {
    public void insertClazz(Clazz clazz) throws PersistenceException {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            session.save(clazz);
            transaction.commit();
            session.close();
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    public List<Clazz> getClazzByMajorId(int majorId) throws PersistenceException {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            String hql = "from Clazz as clazz where majorId=" + majorId;
            List<Clazz> list = session.createQuery(hql).list();
            transaction.commit();
            session.close();
            return list;
        }catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    public List<Clazz> getClazzByName(String name) throws PersistenceException {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            String hql = "from Clazz as clazz where name=" + name;
            List<Clazz> list = session.createQuery(hql).list();
            transaction.commit();
            session.close();
            return list;
        }catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }
}
