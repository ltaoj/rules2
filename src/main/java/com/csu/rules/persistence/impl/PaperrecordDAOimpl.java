package com.csu.rules.persistence.impl;

import com.csu.rules.domain.Contestregistion;
import com.csu.rules.domain.Paper;
import com.csu.rules.domain.Paperrecord;
import com.csu.rules.exception.PersistenceException;
import com.csu.rules.persistence.PaperrecordDAO;
import com.csu.rules.utils.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by ltaoj on 17-7-17.
 */
@Repository
public class PaperrecordDAOimpl implements PaperrecordDAO{
    public void insertPaperrecord(Paperrecord paperrecord) throws PersistenceException {
        try{
            Session session= HibernateUtil.getSession();
            Transaction transaction=session.beginTransaction();
            session.save(paperrecord);
            transaction.commit();
            session.close();
        }catch(RuntimeException e){
            throw new PersistenceException(e);
        }
    }

    public void deletePaperrecord(int paperId) throws PersistenceException {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction=session.beginTransaction();
            Paperrecord paperrecord=(Paperrecord) session.get(Paperrecord.class,new Integer(paperId));
            session.delete(paperrecord);
            transaction.commit();
            session.close();
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    public void deletePaperrecordByStudentId(int studentId) throws PersistenceException {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction=session.beginTransaction();
            Query query=session.createQuery("delete Paperrecord as paperrecord where studentId=?");
            query.setInteger(0,studentId);
            query.executeUpdate();
            transaction.commit();
            session.close();
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    public void deletePaperrecordByTestId(int testId) throws PersistenceException {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction=session.beginTransaction();
            Query query=session.createQuery("delete Paperrecord as paperrecord where testId=?");
            query.setInteger(0,testId);
            query.executeUpdate();
            transaction.commit();
            session.close();
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    public void deletePaperrecordByStudentIdAndTestId(int studentId, int testId) throws PersistenceException {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction=session.beginTransaction();
            Query query=session.createQuery("delete Paperrecord as paperrecord where studentId=? and testId=?");
            query.setInteger(0,studentId);
            query.setInteger(1,testId);
            query.executeUpdate();
            transaction.commit();
            session.close();
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    public Paperrecord getPaperrecord(int paper_id) throws PersistenceException {
        try {
            Session session = HibernateUtil.getSession();
            Paperrecord paperrecord=(Paperrecord)session.get(Paperrecord.class,new Integer(paper_id));
            session.close();
            return null;
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    public Paperrecord getPaperrecordByStudentIdAndTestId(int studentId, int testId) throws PersistenceException {
        try {
            Session session = HibernateUtil.getSession();
            Query query=session.createQuery("from Paperrecord as paperrecord where studentId=? and testId=?");
            query.setInteger(0,studentId);
            query.setInteger(1,testId);
            List<Paperrecord> paperrecordList=query.list();
            session.close();
            return paperrecordList != null && paperrecordList.size() > 0 ? paperrecordList.get(0) : null;
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }

    }

    public List<Paperrecord> getPaperrecordByStudentId(int studentId) throws PersistenceException {
        try {
            Session session = HibernateUtil.getSession();
            Criteria criteria = session.createCriteria(Paperrecord.class);
            criteria.add(Restrictions.eq("studentId", studentId));
            List list = criteria.list();
            session.close();
            return list;
        }catch (RuntimeException e){
            throw new PersistenceException(e);
        }
    }

    public void updatePaperrecord(int papaerId,Paperrecord paperrecord) throws PersistenceException {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction=session.beginTransaction();
            Paperrecord paperrecord1=(Paperrecord) session.get(Paperrecord.class,new Integer(papaerId));
            paperrecord1.setBlankScore(paperrecord.getBlankScore());
            paperrecord1.setCaseAnswer(paperrecord.getCaseAnswer());
            paperrecord1.setChoiceScore(paperrecord.getChoiceScore());
            paperrecord1.setDiscussAnswer(paperrecord.getDiscussAnswer());
            paperrecord1.setJudgeScore(paperrecord.getJudgeScore());
            paperrecord1.setShortAnswer(paperrecord.getShortAnswer());
            paperrecord1.setSubmitTime(paperrecord.getSubmitTime());
            session.update(paperrecord1);
            transaction.commit();
            session.close();
        }catch (RuntimeException e){
            throw new PersistenceException(e);
        }
    }

    public void updatePaperrecordBy(int studentId, int testId, Paperrecord paperrecord) throws PersistenceException {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction=session.beginTransaction();
            Query query=session.createQuery("from Paperrecord as paperrecord where studentId=? and testId=?");
            query.setInteger(0,studentId);
            query.setInteger(1,testId);
            List<Paperrecord> paperrecordList=query.list();
            Paperrecord paperrecord1=paperrecordList.get(0);
            paperrecord1.setBlankScore(paperrecord.getBlankScore());
            paperrecord1.setCaseAnswer(paperrecord.getCaseAnswer());
            paperrecord1.setChoiceScore(paperrecord.getChoiceScore());
            paperrecord1.setDiscussAnswer(paperrecord.getDiscussAnswer());
            paperrecord1.setJudgeScore(paperrecord.getJudgeScore());
            paperrecord1.setShortAnswer(paperrecord.getShortAnswer());
            paperrecord1.setSubmitTime(paperrecord.getSubmitTime());
            session.update(paperrecord1);
            transaction.commit();
            session.close();
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }
}