package com.csu.rules.persistence.impl;

import com.csu.rules.domain.Account;
import com.csu.rules.domain.Contestregistion;
import com.csu.rules.domain.Paper;
import com.csu.rules.domain.Paperrecord;
import com.csu.rules.exception.PersistenceException;
import com.csu.rules.persistence.AbstractDAO;
import com.csu.rules.persistence.PaperrecordDAO;
import com.csu.rules.utils.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ltaoj on 17-7-17.
 */
@Repository
public class PaperrecordDAOimpl extends AbstractDAO implements PaperrecordDAO{
    public void insertPaperrecord(Paperrecord paperrecord) throws PersistenceException {
            Session session= HibernateUtil.getSession();
        Transaction transaction = getTransation(session);
        try{
            session.save(paperrecord);
            session.flush();
            transaction.commit();
        }catch(RuntimeException e){
            transaction.rollback();
            throw new PersistenceException(e);
        }finally {
            session.close();
        }
    }

    public void deletePaperrecord(int paperId) throws PersistenceException {
            Session session = HibernateUtil.getSession();
        Transaction transaction = getTransation(session);
        try {
            Paperrecord paperrecord=(Paperrecord) session.get(Paperrecord.class,new Integer(paperId));
            session.delete(paperrecord);
            session.flush();
            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            throw new PersistenceException(e);
        }finally {
            session.close();
        }
    }

    public void deletePaperrecordByStudentId(String studentId) throws PersistenceException {
            Session session = HibernateUtil.getSession();
        Transaction transaction = getTransation(session);
        try {
            Query query=session.createQuery("delete Paperrecord as paperrecord where studentId=?");
            query.setString(0,studentId);
            query.executeUpdate();
            session.flush();
            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            throw new PersistenceException(e);
        }finally {
            session.close();
        }
    }

    public void deletePaperrecordByTestId(int testId) throws PersistenceException {
            Session session = HibernateUtil.getSession();
        Transaction transaction = getTransation(session);
        try {
            Query query=session.createQuery("delete Paperrecord as paperrecord where testId=?");
            query.setInteger(0,testId);
            query.executeUpdate();
            session.flush();
            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            throw new PersistenceException(e);
        }finally {
            session.close();
        }
    }

    public void deletePaperrecordByStudentIdAndTestId(String studentId, int testId) throws PersistenceException {
            Session session = HibernateUtil.getSession();
        Transaction transaction = getTransation(session);
        try {
            Query query=session.createQuery("delete Paperrecord as paperrecord where studentId=? and testId=?");
            query.setString(0,studentId);
            query.setInteger(1,testId);
            query.executeUpdate();
            session.flush();
            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            throw new PersistenceException(e);
        }finally {
            session.close();
        }
    }

    public Paperrecord getPaperrecord(int paper_id) throws PersistenceException {
            Session session = HibernateUtil.getSession();
        Transaction transaction = getTransation(session);
        try {
            Paperrecord paperrecord=(Paperrecord)session.get(Paperrecord.class,new Integer(paper_id));
            session.flush();
            transaction.commit();
            return paperrecord;
        } catch (RuntimeException e) {
            transaction.rollback();
            throw new PersistenceException(e);
        }finally {
            session.close();
        }
    }

    public Paperrecord getPaperrecordByStudentIdAndTestId(String studentId, int testId) throws PersistenceException {
            Session session = HibernateUtil.getSession();
        Transaction transaction = getTransation(session);
        try {
            Query query=session.createQuery("from Paperrecord as paperrecord where studentId=? and testId=? and isCorrected=?");
            query.setString(0,studentId);
            query.setInteger(1,testId);
            query.setInteger(2,0);
            List<Paperrecord> paperrecordList=query.list();
            session.flush();
            transaction.commit();
            return paperrecordList != null && paperrecordList.size() > 0 ? paperrecordList.get(0) : null;
        } catch (RuntimeException e) {
            transaction.rollback();
            throw new PersistenceException(e);
        }finally {
            session.close();
        }

    }

    public List<Paperrecord> getPaperrecordByStudentId(String studentId) throws PersistenceException {
            Session session = HibernateUtil.getSession();
        Transaction transaction = getTransation(session);
        try {
            Criteria criteria = session.createCriteria(Paperrecord.class);
            criteria.add(Restrictions.eq("studentId", studentId));
            List list = criteria.list();
            session.flush();
            transaction.commit();
            return list;
        }catch (RuntimeException e){
            transaction.rollback();
            throw new PersistenceException(e);
        }finally {
            session.close();
        }
    }

    public void updatePaperrecord(int papaerId,Paperrecord paperrecord) throws PersistenceException {
            Session session = HibernateUtil.getSession();
        Transaction transaction = getTransation(session);
        try {
            Paperrecord paperrecord1=(Paperrecord) session.get(Paperrecord.class,new Integer(papaerId));
            paperrecord1.setBlankScore(paperrecord.getBlankScore());
            paperrecord1.setCaseAnswer(paperrecord.getCaseAnswer());
            paperrecord1.setChoiceScore(paperrecord.getChoiceScore());
            paperrecord1.setDiscussAnswer(paperrecord.getDiscussAnswer());
            paperrecord1.setJudgeScore(paperrecord.getJudgeScore());
            paperrecord1.setShortAnswer(paperrecord.getShortAnswer());
            paperrecord1.setSubmitTime(paperrecord.getSubmitTime());
            paperrecord1.setIsCorrected(paperrecord.getIsCorrected());
            session.update(paperrecord1);
            session.flush();
            transaction.commit();
        }catch (RuntimeException e){
            transaction.rollback();
            throw new PersistenceException(e);
        }finally {
            session.close();
        }
    }

    public void updatePaperrecordBy(String studentId, int testId, Paperrecord paperrecord) throws PersistenceException {
            Session session = HibernateUtil.getSession();
        Transaction transaction = getTransation(session);
        try {
            Query query=session.createQuery("from Paperrecord as paperrecord where studentId=? and testId=?");
            query.setString(0,studentId);
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
            paperrecord1.setIsCorrected(paperrecord.getIsCorrected());
            session.update(paperrecord1);
            session.flush();
            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            throw new PersistenceException(e);
        }finally {
            session.close();
        }
    }

    public List<Paperrecord> getPaperRecordListByTestid(int testId) throws PersistenceException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = getTransation(session);
        try {
            Criteria criteria = session.createCriteria(Paperrecord.class);
            criteria.add(Restrictions.eq("testId", testId));
            criteria.add(Restrictions.eq("isCorrected", 0));
            List list = criteria.list();
            session.flush();
            transaction.commit();
            return list;
        }catch (RuntimeException e){
            transaction.rollback();
            throw new PersistenceException(e);
        }finally {
            session.close();
        }
    }


}