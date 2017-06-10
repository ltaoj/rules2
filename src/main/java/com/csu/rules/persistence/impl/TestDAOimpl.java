package com.csu.rules.persistence.impl;

import com.csu.rules.domain.Testinfo;
import com.csu.rules.persistence.TestDAO;
import com.csu.rules.utils.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceException;
import java.util.List;

/**
 * Created by GF on 2017/6/10.
 */
@Repository
public class TestDAOimpl implements TestDAO{
    /*
  *返回考试相关信息
  * type:0 考试
  * type:1 竞赛
  * */
    public List<Testinfo> getTestInfoList() throws PersistenceException {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Testinfo.class);
            criteria.add(Restrictions.eq("type", new Integer(0).byteValue()));
            List<Testinfo> testinfoList = criteria.list();
            return testinfoList;
        }catch (RuntimeException e){
            throw new PersistenceException(e);
        }
    }

    public void insertTestInfo(Testinfo testinfo) throws PersistenceException {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            session.save(testinfo);
            transaction.commit();
        }catch (RuntimeException e){
            throw new PersistenceException(e);
        }
    }

    public void deleteTestInfo(int testId) throws PersistenceException {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            Testinfo testinfo=(Testinfo)session.get(Testinfo.class,new Integer(testId));
            session.delete(testinfo);
            transaction.commit();
        }catch (RuntimeException e){
            throw new PersistenceException(e);
        }
    }

    public void updateTestInfo(Testinfo testinfo) throws PersistenceException {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            session.update(testinfo);
            transaction.commit();
        }catch (RuntimeException e){
            throw new PersistenceException(e);
        }
    }

    public Testinfo getTestInfo(int testId) throws PersistenceException {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            Testinfo testinfo=(Testinfo)session.get(Testinfo.class,new Integer(testId));
            return testinfo;
        }catch (RuntimeException e){
            throw new PersistenceException(e);
        }
    }
}
