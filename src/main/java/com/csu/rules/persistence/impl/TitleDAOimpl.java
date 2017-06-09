package com.csu.rules.persistence.impl;

import com.csu.rules.domain.Option;
import com.csu.rules.domain.Title;
import com.csu.rules.domain.TitleOption;
import com.csu.rules.persistence.TitleDAO;
import com.csu.rules.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceException;
import java.util.List;

/**
 * Created by ltaoj on 17-6-9.
 */
@Repository
public class TitleDAOimpl implements TitleDAO {
    public void addTitle(Title title, List<Option> optionList) throws PersistenceException {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            Integer titleId = (Integer) session.save(title);
            System.out.println(titleId);
            transaction.commit();
            session.close();
            for (int i = 0;i < optionList.size();i++) {
                session = HibernateUtil.getSession();
                transaction = session.beginTransaction();
                optionList.get(i).setTitleId(titleId);
                Integer optionId = (Integer) session.save(optionList.get(i));
                System.out.println(optionId);
                transaction.commit();
                session.close();
            }
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    public void addTitle(TitleOption titleOption) throws PersistenceException {

    }

    public TitleOption deleteTitle(int titleId) throws PersistenceException {
        return null;
    }

    public TitleOption editTitle(int titleId) throws PersistenceException {
        return null;
    }

    public List<TitleOption> getTitleOptionList(int offset, int count) throws PersistenceException {
        return null;
    }

    public List<TitleOption> getRandomTitleOptionList(int count) throws PersistenceException {
        return null;
    }

    public List<TitleOption> getTitleOptionListByTitleId(int[] titleIds) throws PersistenceException {
        return null;
    }
}
