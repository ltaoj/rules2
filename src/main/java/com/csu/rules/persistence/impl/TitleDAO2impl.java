package com.csu.rules.persistence.impl;

import com.csu.rules.exception.PersistenceException;
import com.csu.rules.persistence.AbstractDAO;
import com.csu.rules.persistence.TitleDAO2;
import com.csu.rules.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ltaoj on 2018/05/01 14:21.
 *
 * @version : 1.0
 */
@Repository
public class TitleDAO2impl extends AbstractDAO implements TitleDAO2 {

    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "titledao", key = "'titlesize'")
    public Long getTotalTitleSize() throws PersistenceException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = getTransation(session);
        try {
            String hql = "select count(*) from Title as title";
            Long count = (Long) session.createQuery(hql).uniqueResult();
            session.flush();
            transaction.commit();
            return count;
        } catch (RuntimeException e) {
            transaction.rollback();
            throw new PersistenceException(e);
        } finally {
            session.close();
        }
    }
}
