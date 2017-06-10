package com.csu.rules.persistence.impl;

import com.csu.rules.domain.Clockin;
import com.csu.rules.exception.PersistenceException;
import com.csu.rules.persistence.ClockDAO;
import com.csu.rules.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by ltaoj on 17-6-10.
 */
public class ClockDAOimpl implements ClockDAO {

    public void insertClock(Clockin clockin) throws PersistenceException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(clockin);
        transaction.commit();
        session.close();
    }

    public List<Clockin> getAllClocks(long studentId) throws PersistenceException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from Clockin as clockin where clockin.studentId=" + studentId;
        List<Clockin> clockinList = session.createQuery(hql).list();
        transaction.commit();
        session.close();
        return clockinList;
    }

    public Clockin getClockByDay(long studentId, Timestamp datetime) throws PersistenceException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from Clockin as clockin where clockin.studentId=" + studentId +
                " and extract(year from clockin.clockDay)=" + (datetime.getYear() + 1900) +
                " and extract(month from clockin.clockDay)=" + (datetime.getMonth() + 1) +
                " and extract(day from clockin.clockDay)=" + (datetime.getDate());
        List<Clockin> clockinList = session.createQuery(hql).list();
        transaction.commit();;
        session.close();
        return clockinList != null && clockinList.size() > 0 ? clockinList.get(0) : null;
    }

    public void updateClock(Clockin clockin) throws PersistenceException {
        Clockin clockin1 = getClockByDay(clockin.getStudentId(),clockin.getClockDay());
        if (clockin1 == null) { insertClock(clockin); return;}
        clockin.setClockId(clockin1.getClockId());
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.update("Clockin", clockin);
        transaction.commit();
        session.close();
    }
}
