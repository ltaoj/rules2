package com.csu.rules.persistence.impl;

import com.csu.rules.domain.Account;
import com.csu.rules.domain.Wrongtitle;
import com.csu.rules.exception.PersistenceException;
import com.csu.rules.persistence.WrongtitleDAO;
import com.csu.rules.utils.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by CMM on 2017/6/10.
 */
@Repository
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

    public Wrongtitle getWrongTitle(Wrongtitle wrongtitle) throws PersistenceException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from Wrongtitle as wrongtitle where studentId=" + wrongtitle.getStudentId() +
                " and titleId=" + wrongtitle.getTitleId();
        List<Wrongtitle> list = session.createQuery(hql).list();
        transaction.commit();
        session.close();
        return (list != null && list.size() > 0) ? list.get(0) : null;
    }
}
