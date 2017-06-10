package com.csu.rules.persistence.impl;

import com.csu.rules.domain.Account;
import com.csu.rules.domain.Wrongtitle;
import com.csu.rules.persistence.WrongtitleDAO;
import com.csu.rules.utils.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import javax.persistence.PersistenceException;
import java.util.List;

/**
 * Created by CMM on 2017/6/10.
 */
public class WrongtitleDAOimpl implements WrongtitleDAO {
    public List<Wrongtitle> getWrongTitleIdList(Account account) throws PersistenceException {
        Session session = HibernateUtil.getSession();
        Criteria criteria = session.createCriteria(Wrongtitle.class);
        criteria.add(Restrictions.eq("studentId", account.getStudentId()));
        List<Wrongtitle> wrongtitleList = criteria.list();
        return wrongtitleList;
    }

    public void insertWrongTitle(Wrongtitle wrongtitle) throws PersistenceException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(wrongtitle);
        transaction.commit();
    }

    public void deleteWrongTitle(Wrongtitle wrongtitle) throws PersistenceException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(wrongtitle);
        transaction.commit();
    }
}
