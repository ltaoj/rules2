package com.csu.rules.persistence.impl;

import com.csu.rules.domain.Notice;
import com.csu.rules.domain.Title;
import com.csu.rules.exception.PersistenceException;
import com.csu.rules.persistence.NoticeDAO;
import com.csu.rules.utils.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by GF on 2017/6/10.
 */
@Repository
public class NoticeDAOimpl implements NoticeDAO{
    public void insertNotice(Notice notice) throws PersistenceException {
        try {
            Session session = HibernateUtil.getSession();
            session.save(notice);
            session.close();
        }catch (RuntimeException e){
            throw new PersistenceException(e);
        }
    }

    public Notice getNotice(int noticeId) throws PersistenceException{
        try {
            Session session = HibernateUtil.getSession();
            Notice notice = (Notice) session.get(Notice.class, new Integer(noticeId));
            return notice;
        }catch (RuntimeException e){
            throw new PersistenceException(e);
        }
    }

    public List<Notice> getTextNoticeList() throws PersistenceException{
        try {
            Session session = HibernateUtil.getSession();
            Criteria criteria = session.createCriteria(Notice.class);
            criteria.add(Restrictions.eq("type", new Integer(0)));
            List<Notice> textNoticeList = criteria.list();
            return textNoticeList;
        }catch (RuntimeException e){
            throw new PersistenceException(e);
        }
    }

    public List<Notice> getPictrueNoticeList() throws PersistenceException{
        try {
            Session session = HibernateUtil.getSession();
            Criteria criteria = session.createCriteria(Notice.class);
            criteria.add(Restrictions.eq("type", new Integer(1)));
            List<Notice> pictureNoticeList = criteria.list();
            return pictureNoticeList;
        }catch (RuntimeException e){
            throw new PersistenceException(e);
        }
    }

    public void deleteNotice(int noticeId) throws PersistenceException {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            Notice notice=(Notice)session.get(Notice.class,new Integer(noticeId));
            session.delete(notice);
            transaction.commit();
        }catch (RuntimeException e){
            throw new PersistenceException(e);
        }
    }

    public void updateNotice(Notice notice) throws PersistenceException {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            session.update(notice);
            transaction.commit();
        }catch (RuntimeException e){
            throw new PersistenceException(e);
        }
    }

    public List<Notice> getTextNoticeListByPage(int offset, int count) throws PersistenceException {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from Notice where type=?");
            query.setInteger(0,0);
            query.setFirstResult(offset);
            query.setMaxResults(count);
            List<Notice> textNoticeList = query.list();
            transaction.commit();
            return textNoticeList;
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    public List<Notice> getPicNoticeListByPage(int offset, int count) throws PersistenceException {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from Notice where type=?");
            query.setFirstResult(offset);//从offset开始查询
            query.setMaxResults(count);//获取count数量的结果
            query.setInteger(0,1);
            List<Notice> PicNoticeList = query.list();
            transaction.commit();
            return PicNoticeList;
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }
}
