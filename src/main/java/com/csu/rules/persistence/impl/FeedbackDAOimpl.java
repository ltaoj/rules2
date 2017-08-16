package com.csu.rules.persistence.impl;

import com.csu.rules.domain.Feedback;
import com.csu.rules.exception.PersistenceException;
import com.csu.rules.persistence.FeedbackDAO;
import com.csu.rules.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by GF on 2017/7/3.
 */
@Repository
public class FeedbackDAOimpl implements FeedbackDAO {
    public void insertFeedback(Feedback feedback) throws PersistenceException {
            Session session= HibernateUtil.getSession();
            Transaction transaction=session.beginTransaction();
        try {
            session.save(feedback);
            session.flush();
            transaction.commit();
        }catch (RuntimeException e){
            transaction.rollback();
            throw new PersistenceException(e);
        }finally {
            session.close();
        }
    }

    public List<Feedback> getFeedbackList() throws PersistenceException {
            Session session= HibernateUtil.getSession();
        try {
            String hql="from Feedback";
            Query query=session.createQuery(hql);
            List<Feedback> list=query.list();
            return list;
        }catch (RuntimeException e){
            throw new PersistenceException(e);
        }finally {
            session.close();
        }
    }
}
