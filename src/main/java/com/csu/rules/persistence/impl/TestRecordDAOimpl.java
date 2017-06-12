package com.csu.rules.persistence.impl;

import com.csu.rules.domain.Testrecord;
import com.csu.rules.exception.PersistenceException;
import com.csu.rules.persistence.TestRecordDAO;
import com.csu.rules.utils.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by GF on 2017/6/10.
 */
@Repository
public class TestRecordDAOimpl implements TestRecordDAO {
    public void insertTestRecord(Testrecord testrecord) throws PersistenceException {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            session.save(testrecord);
            transaction.commit();
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    public void updateTestRecord(Testrecord testrecord) throws PersistenceException {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            session.update(testrecord);
            transaction.commit();
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    public Testrecord getTestRecord(Testrecord testrecord) throws PersistenceException {
        try {
            Session session = HibernateUtil.getSession();
            Criteria criteria=session.createCriteria(Testrecord.class);
            criteria.add(Restrictions.eq("studentId",new Long(testrecord.getStudentId())));
            criteria.add(Restrictions.eq("testId",testrecord.getTestId()));
            List<Testrecord> list=criteria.list();
            if (list.size()!=0) {
                Testrecord testRecord = list.get(0);
                return testRecord;
            }else{
                return null;
            }
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    public List<Testrecord> getTestRecordList(int testId) throws PersistenceException {
        try {
            Session session = HibernateUtil.getSession();
            Criteria criteria=session.createCriteria(Testrecord.class);
            criteria.add(Restrictions.eq("testId",testId));
            List<Testrecord> testrecordList=criteria.list();
            return testrecordList;
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }
}
