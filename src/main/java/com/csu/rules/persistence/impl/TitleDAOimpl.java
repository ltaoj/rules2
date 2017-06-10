package com.csu.rules.persistence.impl;

import com.csu.rules.domain.Option;
import com.csu.rules.domain.Title;
import com.csu.rules.exception.PersistenceException;
import com.csu.rules.persistence.TitleDAO;
import com.csu.rules.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Created by ltaoj on 17-6-9.
 */
@Repository
public class TitleDAOimpl implements TitleDAO {

    public Integer addTitle(Title title) throws PersistenceException {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            Integer titleId = (Integer) session.save(title);
            transaction.commit();
            session.close();
            return titleId;
        }catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    public Title deleteTitle(int titleId) throws PersistenceException {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            Title title = session.get(Title.class, titleId);
            System.out.println(title);
            session.delete(title);
            transaction.commit();
            session.close();
            return title;
        } catch (RuntimeException e) {
             throw new PersistenceException(e);
        }
    }

    public Title editTitle(Title title, Option option) throws PersistenceException {
        return null;
    }

    public Title getTitle(int titleId) throws javax.persistence.PersistenceException {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            String hql = "from Title as title where title.titleId=" + titleId;
            List<Title> list = session.createQuery(hql).list();
            transaction.commit();
            session.close();
            return list.size() > 0 ? list.get(0) : null;
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    public List<Title> getTitleList(int offset, int count) throws PersistenceException {
            try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from Title");
            query.setFirstResult(offset);
            query.setFetchSize(count);
            List<Title> titleList = query.list();
            transaction.commit();
            session.close();
            return titleList;
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    public List<Title> getRandomTitleList(int count) throws PersistenceException {
        try {
            long totalSize = getTotalTitleSize();
            if (count > totalSize) count = (int) totalSize;
            Set<Integer> set = randomIntegerList(count, totalSize);
            return getTitleListByTitleIds(set);
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    public List<Title> getTitleListByTitleIds(Set<Integer> titleIds) throws PersistenceException {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            String hql = "from Title as title where title.titleId in (" + formatSet(titleIds) + ")";
            List<Title> list = session.createQuery(hql).list();
            transaction.commit();
            session.close();
            return list;
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    public Long getTotalTitleSize() throws PersistenceException {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            String hql = "select count(*) from Title as title";
            Long count = (Long) session.createQuery(hql).uniqueResult();
            transaction.commit();
            session.close();
            return count;
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    /**
     * @param count 要返回的数目
     * @param max 要返回的最大值
     * @return
     * 返回小于max的count个正整数
     */
    private Set<Integer> randomIntegerList(int count, long max) {
        Set<Integer> set = new HashSet<Integer>();
        while (set.size() < count) {
            Integer integer = (int)(Math.random() * max);
            set.add(integer);
        }
        return set;
    }

    private String formatSet(Set set) {
        StringBuilder sb = new StringBuilder();
        Iterator values = set.iterator();
        while (values.hasNext()) {
            sb.append("," + values.next());
        }
        System.out.println(sb.subSequence(1, sb.length()).toString());
        return sb.subSequence(1, sb.length()).toString();
    }
}
