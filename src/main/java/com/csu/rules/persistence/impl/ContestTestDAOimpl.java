package com.csu.rules.persistence.impl;

import com.csu.rules.domain.Account;
import com.csu.rules.domain.Contestregistion;
import com.csu.rules.domain.Testinfo;
import com.csu.rules.exception.PersistenceException;
import com.csu.rules.persistence.ContestTestDAO;
import com.csu.rules.utils.HibernateUtil;
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

    public void registContest(Account account, Testinfo testInfo) throws PersistenceException {
        Session session= HibernateUtil.getSession();
        Transaction transaction=session.beginTransaction();
        Contestregistion contestRegistion=new Contestregistion();
        contestRegistion.setStudentId(account.getStudentId());
        contestRegistion.setTestId(testInfo.getTestId());
        contestRegistion.setStatus(0);
        try {
            session.save(contestRegistion);
            transaction.commit();
        }catch (RuntimeException e){
            throw new PersistenceException(e);
        }
    }

    public Contestregistion isRegistedContest(Account account, Testinfo testInfo) throws PersistenceException{
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Contestregistion.class);
            criteria.add(Restrictions.eq("studentId", account.getStudentId()));
            criteria.add(Restrictions.eq("testId", testInfo.getTestId()));
            List list = criteria.list();
            Contestregistion contestRegistion = (Contestregistion) list.get(0);
            return contestRegistion;
        }catch (RuntimeException e){
            throw new PersistenceException(e);
        }
    }

    public List<Contestregistion> getContestRegistionList(Testinfo testInfo) throws PersistenceException{
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Contestregistion.class);
            criteria.add(Restrictions.eq("testId", testInfo.getTestId()));
            List<Contestregistion> contsetRegistionList = criteria.list();
            return contsetRegistionList;
        }catch (RuntimeException e){
            throw new PersistenceException(e);
        }
    }

    public Contestregistion changeContestStatusBegin(Contestregistion contestregistion) throws PersistenceException{
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            contestregistion.setStatus(1);
            session.update(contestregistion);
            transaction.commit();
            return contestregistion;
        }catch (RuntimeException e){
            throw new PersistenceException(e);
        }
    }

    public Contestregistion changeContestStatusEnd(Contestregistion contestregistion) throws PersistenceException{
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            contestregistion.setStatus(2);
            session.update(contestregistion);
            transaction.commit();
            return contestregistion;
        }catch (RuntimeException e){
            throw new PersistenceException(e);
        }
    }

    public List<Testinfo> getContestInfoList() throws PersistenceException {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Testinfo.class);
            criteria.add(Restrictions.eq("type", new Integer(1).byteValue()));
            List<Testinfo> contestinfoList = criteria.list();
            return contestinfoList;
        }catch (RuntimeException e){
            throw new PersistenceException(e);
        }
    }
}
