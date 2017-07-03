package com.csu.rules.persistence.impl;

import com.csu.rules.domain.Account;
import com.csu.rules.domain.Admin;
import com.csu.rules.exception.PersistenceException;
import com.csu.rules.persistence.AdminDAO;
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
public class AdminDAOimpl implements AdminDAO {

    public Admin Login(Admin admin) throws PersistenceException {
        try {
            Session session = HibernateUtil.getSession();
            Criteria criteria = session.createCriteria(Admin.class);
            criteria.add(Restrictions.eq("account", admin.getAccount()));
            criteria.add(Restrictions.eq("password", admin.getPassword()));
            List<Admin> list = criteria.list();
            session.close();
            return list.size() > 0 ? list.get(0) : null;
        }catch (RuntimeException e){
            throw new PersistenceException(e);
        }
    }

    public void insertAdmin(Admin admin) throws PersistenceException {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            session.save(admin);
            transaction.commit();
            session.close();
        }catch (RuntimeException e){
            throw new PersistenceException(e);
        }
    }
}
