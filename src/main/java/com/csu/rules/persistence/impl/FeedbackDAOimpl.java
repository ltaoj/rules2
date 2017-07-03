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
        try {
            Session session= HibernateUtil.getSession();
            Transaction transaction=session.beginTransaction();
            session.save(feedback);
            transaction.commit();
            session.close();
        }catch (RuntimeException e){
            throw new PersistenceException(e);
        }
    }

    public List<Feedback> getFeedbackList() throws PersistenceException {
        try {
            Session session= HibernateUtil.getSession();
            String hql="from Feedback";
            Query query=session.createQuery(hql);
            List<Feedback> list=query.list();
            session.close();
            return list;
        }catch (RuntimeException e){
            throw new PersistenceException(e);
        }
    }
}
