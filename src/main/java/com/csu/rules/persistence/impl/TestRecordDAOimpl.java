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

import javax.management.Query;
import java.sql.Timestamp;
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
            return list.size() > 0 ? list.get(0) : null;
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

    public List<Testrecord> getTestRecordListByRecord(int testId) throws PersistenceException {
        try {
            Session session = HibernateUtil.getSession();
            String hql="from Testrecord as testrecord where testrecord.testId=? order by score desc";
            org.hibernate.query.Query query=session.createQuery(hql);
            query.setInteger(0,testId);
            List<Testrecord> testrecordList=query.list();
            return testrecordList;
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    public void deleteTestRecord(Testrecord testrecord) throws PersistenceException {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction=session.beginTransaction();
            session.delete(testrecord);
            transaction.commit();
            session.close();
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    public List<Testrecord> getTestrecordByCondition(String clazz, int grade, String major, String college, int level) throws PersistenceException {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            String hql = formatHql(clazz, grade, major, college, level);
            List<Testrecord> list = session.createQuery(hql).list();
            transaction.commit();
            session.close();
            return list;
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    private String formatHql(String clazz, int grade, String major, String college, int level) {
        String hql =  "from Testrecord as testrecord where ";
        int initSize = hql.length();
        hql += (clazz != null && !clazz.equals("") ? "clazz=" + clazz + " and " : "");
        hql += (grade != 0 ? "grade=" + grade + " and "  : "");
        hql += (major != null && !major.equals("") ? "major=" + major + " and " : "");
        hql += (college != null && !college.equals("") ? "college=" + college + " and " : "");
        hql += (level != 0 ? "score=" + level + " and " : "");
        // 如果四个字段全没有，即字符串为null或者空字符，数字为0, 此时查询全校记录
        if (hql.length() == initSize) {
            hql = "from Testrecord";
        } else {
            hql = hql.substring(0, hql.length() - 5);
        }
        return hql;
    }
}
