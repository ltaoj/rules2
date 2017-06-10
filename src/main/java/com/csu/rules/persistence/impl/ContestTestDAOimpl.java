package com.csu.rules.persistence.impl;

import com.csu.rules.domain.Account;
import com.csu.rules.domain.Contestregistion;
import com.csu.rules.domain.Testinfo;
import com.csu.rules.persistence.ContestTestDAO;
import com.csu.rules.utils.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceException;
import java.util.List;

/**
 * Created by GF on 2017/6/8.
 */
@Repository
public class ContestTestDAOimpl implements ContestTestDAO {
        /*
        * 报名竞赛
        * status
        * 0.未开始
        * 1.开始
        * 2.已结束
        * */
    public void registContest(Account account, Testinfo testInfo) throws PersistenceException{
        Session session= HibernateUtil.getSession();
        Transaction transaction=session.beginTransaction();
        Contestregistion contestRegistion=new Contestregistion();
        contestRegistion.setStudentId(account.getStudentId());
        contestRegistion.setTestId(testInfo.getTestId());
        contestRegistion.setStatus(0);
        try {
            session.save(contestRegistion);
            transaction.commit();
            session.close();
        }catch (RuntimeException e){
            throw new PersistenceException(e);
        }
    }

    //判断是否已经报名竞赛
    public Contestregistion isRegistedContest(Account account, Testinfo testInfo) throws PersistenceException{
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Contestregistion.class);
            criteria.add(Restrictions.eq("studentId", account.getStudentId()));
            criteria.add(Restrictions.eq("testId", testInfo.getTestId()));
            List list = criteria.list();
            Contestregistion contestRegistion = (Contestregistion) list.get(0);
            session.close();
            return contestRegistion;
        }catch (RuntimeException e){
            throw new PersistenceException(e);
        }
    }

    //根据竞赛id查看已经报名竞赛的人
    public List<Contestregistion> getContestRegistionList(Testinfo testInfo) throws PersistenceException{
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Contestregistion.class);
            criteria.add(Restrictions.eq("testId", testInfo.getTestId()));
            List<Contestregistion> contsetRegistionList = criteria.list();
            session.close();
            return contsetRegistionList;
        }catch (RuntimeException e){
            throw new PersistenceException(e);
        }
    }

    /*
    * 改变竞赛考试的状态status为开始
    * status:0.未开始
    * status:1.开始
    * status:2.已结束
    * */
    public Contestregistion changeContestStatusBegin(Contestregistion contestregistion) throws PersistenceException{
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            contestregistion.setStatus(1);
            session.update(contestregistion);
            transaction.commit();
            session.close();
            return contestregistion;
        }catch (RuntimeException e){
            throw new PersistenceException(e);
        }
    }

    /*
   * 改变竞赛考试的状态status为结束
   * status:0.未开始
   * status:1.开始
   * status:2.已结束
   * */
    public Contestregistion changeContestStatusEnd(Contestregistion contestregistion) throws PersistenceException{
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            contestregistion.setStatus(2);
            session.update(contestregistion);
            transaction.commit();
            session.close();
            return contestregistion;
        }catch (RuntimeException e){
            throw new PersistenceException(e);
        }
    }

    /*
    *返回竞赛相关信息
    * type:0 考试
    * type:1 竞赛
    * */
    public List<Testinfo> getContestInfo() throws PersistenceException {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Testinfo.class);
            criteria.add(Restrictions.eq("type", new Integer(1).byteValue()));
            List<Testinfo> contestinfoList = criteria.list();
            session.close();
            return contestinfoList;
        }catch (RuntimeException e){
            throw new PersistenceException(e);
        }
    }

    /*
   *返回考试相关信息
   * type:0 考试
   * type:1 竞赛
   * */
    public List<Testinfo> getTestInfo() throws PersistenceException {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Testinfo.class);
            criteria.add(Restrictions.eq("type", 0));
            List<Testinfo> testinfoList = criteria.list();
            return testinfoList;
        }catch (RuntimeException e){
            throw new PersistenceException(e);
        }
    }


}
