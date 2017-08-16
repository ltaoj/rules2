package com.csu.rules.persistence.impl;

import com.csu.rules.domain.Account;
import com.csu.rules.domain.Contestregistion;
import com.csu.rules.domain.Testinfo;
import com.csu.rules.exception.PersistenceException;
import com.csu.rules.persistence.ContestTestDAO;
import com.csu.rules.utils.HibernateUtil;
import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by GF on 2017/6/8.
 */
@Repository
public class ContestTestDAOimpl implements ContestTestDAO {

    public void registContest(Contestregistion contestregistion) throws PersistenceException {
        Session session= HibernateUtil.getSession();
        Transaction transaction=session.beginTransaction();
        Contestregistion contest=new Contestregistion();
        contest.setStudentId(contestregistion.getStudentId());
        contest.setTestId(contestregistion.getTestId());
        contest.setStatus(0);
        try {
            session.save(contest);
            session.flush();
            transaction.commit();
        }catch (RuntimeException e){
            transaction.rollback();
            throw new PersistenceException(e);
        }finally {
            session.close();
        }
    }

    public Contestregistion isRegistedContest(Contestregistion contestregistion) throws PersistenceException{
            Session session = HibernateUtil.getSession();
        try {
            Criteria criteria = session.createCriteria(Contestregistion.class);
            criteria.add(Restrictions.eq("studentId", contestregistion.getStudentId()));
            criteria.add(Restrictions.eq("testId", contestregistion.getTestId()));
            List list = criteria.list();
            if(list.size()!=0) {
                Contestregistion contest = (Contestregistion) list.get(0);
                return contest;
            }else{
                Contestregistion contest=new Contestregistion();
                return contest;
            }
        }catch (RuntimeException e){
            throw new PersistenceException(e);
        }finally {
            session.close();
        }
    }

    public List<Contestregistion> getContestRegistionList(Testinfo testInfo) throws PersistenceException{
            Session session = HibernateUtil.getSession();
        try {
            Criteria criteria = session.createCriteria(Contestregistion.class);
            criteria.add(Restrictions.eq("testId", testInfo.getTestId()));
            List<Contestregistion> contsetRegistionList = criteria.list();
            return contsetRegistionList;
        }catch (RuntimeException e){
            throw new PersistenceException(e);
        }finally {
            session.close();
        }
    }

    public void changeContestStatusBegin(Contestregistion contestregistion) throws PersistenceException{
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
        try {
            contestregistion.setStatus(1);
            session.update(contestregistion);
            session.flush();
            transaction.commit();
        }catch (RuntimeException e){
            transaction.rollback();
            throw new PersistenceException(e);
        }finally {
            session.close();
        }
    }

    public void changeContestStatusEnd(Contestregistion contestregistion) throws PersistenceException{
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
        try {
            contestregistion.setStatus(2);
            session.update(contestregistion);
            session.flush();
            transaction.commit();
        }catch (RuntimeException e){
            transaction.rollback();
            throw new PersistenceException(e);
        }finally {
            session.close();
        }
    }

    public Contestregistion getContestRegistion(Contestregistion contestregistion) throws PersistenceException {
            Session session = HibernateUtil.getSession();
        try {
            Criteria criteria = session.createCriteria(Contestregistion.class);
            criteria.add(Restrictions.eq("studentId", contestregistion.getStudentId()));
            criteria.add(Restrictions.eq("testId", contestregistion.getTestId()));
            List list = criteria.list();
            Contestregistion contest = (Contestregistion) list.get(0);
            return contest;
        }catch (RuntimeException e){
            throw new PersistenceException(e);
        }finally {
            session.close();
        }

    }

    public List<Testinfo> getContestInfoList() throws PersistenceException {
            Session session = HibernateUtil.getSession();
        try {
            String hql="from Testinfo as testinfo where testinfo.type=? order by testId desc";
            org.hibernate.query.Query query=session.createQuery(hql);
            query.setInteger(0,new Integer(1).byteValue());
            List<Testinfo> contestinfoList=query.list();
            return contestinfoList;
        }catch (RuntimeException e){
            throw new PersistenceException(e);
        }finally {
            session.close();
        }
    }
}
