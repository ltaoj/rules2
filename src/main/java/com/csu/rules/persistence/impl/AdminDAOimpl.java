package com.csu.rules.persistence.impl;

import com.csu.rules.domain.Admin;
import com.csu.rules.persistence.AdminDAO;
import com.csu.rules.utils.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * Created by CMM on 2017/6/10.
 */
public class AdminDAOimpl implements AdminDAO {

    public Admin Login(Admin admin) {
        Session session = HibernateUtil.getSession();
        Criteria criteria = session.createCriteria(Admin.class);
        criteria.add(Restrictions.eq("adminId", admin.getAdminId()));
        admin = (Admin) criteria.list().get(0);
        return admin;
    }
}
