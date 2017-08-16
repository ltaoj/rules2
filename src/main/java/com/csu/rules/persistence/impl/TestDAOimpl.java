package com.csu.rules.persistence.impl;

import com.csu.rules.domain.Testinfo;
import com.csu.rules.exception.PersistenceException;
import com.csu.rules.persistence.AbstractDAO;
import com.csu.rules.persistence.TestDAO;
import com.csu.rules.utils.HibernateUtil;
import org.hibernate.CacheMode;
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
public class TestDAOimpl extends AbstractDAO implements TestDAO{
    /*
  *返回考试相关信息
  * type:0 考试
  * type:1 竞赛
  * */
    public List<Testinfo> getTestInfoList() throws PersistenceException {
            Session session = HibernateUtil.getSession();
        try {
            String hql="from Testinfo as testinfo where testinfo.type=? order by testId desc";
            org.hibernate.query.Query query=session.createQuery(hql);
            query.setInteger(0,new Integer(0).byteValue());
            List<Testinfo> tsetinfoList=query.list();
            session.flush();
            return tsetinfoList;
        }catch (RuntimeException e){
            throw new PersistenceException(e);
        }finally {
            session.close();
        }
    }

    public void insertTestInfo(Testinfo testinfo) throws PersistenceException {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
        try {
            session.save(testinfo);
            session.flush();
            transaction.commit();
        }catch (RuntimeException e){
            transaction.rollback();
            throw new PersistenceException(e);
        }finally {
            session.close();
        }
    }

    public void deleteTestInfo(int testId) throws PersistenceException {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
        try {
            Testinfo testinfo=(Testinfo)session.get(Testinfo.class,new Integer(testId));
            session.delete(testinfo);
            session.flush();
            transaction.commit();
        }catch (RuntimeException e){
            transaction.rollback();
            throw new PersistenceException(e);
        }finally {
            session.close();
        }
    }

    public void updateTestInfo(Testinfo testinfo) throws PersistenceException {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
        try {
            session.update(testinfo);
            session.flush();
            transaction.commit();
        }catch (RuntimeException e){
            transaction.rollback();
            throw new PersistenceException(e);
        }finally {
            session.close();
        }
    }

    public Testinfo getTestInfo(int testId) throws PersistenceException {
            Session session = HibernateUtil.getSession();
        try {
            Testinfo testinfo=(Testinfo)session.get(Testinfo.class,new Integer(testId));
            session.flush();
            return testinfo;
        }catch (RuntimeException e){
            throw new PersistenceException(e);
        }finally {
            session.close();
        }
    }
}
