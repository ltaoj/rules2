package com.csu.rules.persistence.impl;

import com.csu.rules.domain.Additiontitle;
import com.csu.rules.exception.PersistenceException;
import com.csu.rules.persistence.AbstractDAO;
import com.csu.rules.persistence.AdditiontitleDAO;
import com.csu.rules.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by ltaoj on 17-7-17.
 */
@Repository
public class AdditiontitleDAOimpl extends AbstractDAO implements AdditiontitleDAO {
    public Integer addTitle(Additiontitle title) throws PersistenceException {
            Session session = HibernateUtil.getSession();
            Transaction transaction = getTransation(session);
        try {
            Integer titleId = (Integer) session.save(title);
            session.flush();
            transaction.commit();
            return titleId;
        } catch (RuntimeException e) {
            transaction.rollback();
            throw new PersistenceException(e);
        }finally {
            session.close();
        }
    }

    public void addTitleList(List<Additiontitle> titleList) throws PersistenceException {
            Session session = HibernateUtil.getSession();
            Transaction transaction = getTransation(session);
        try {
            for (int i = 0;i < titleList.size();i++) {
                session.save(titleList.get(i));
            }
            session.flush();
            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            throw new PersistenceException(e);
        }finally {
            session.close();
        }
    }

    public Additiontitle getTitle(int titleId) throws PersistenceException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = getTransation(session);
        try {
            Additiontitle title = session.get(Additiontitle.class, titleId);
            session.flush();
            transaction.commit();
            return title;
        } catch (RuntimeException e) {
            transaction.rollback();
            throw new PersistenceException(e);
        }finally {
            session.close();
        }
    }

    public List<Additiontitle> getTitleListByType(int type) throws PersistenceException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = getTransation(session);
        try {
            String hql = "from Additiontitle as additiontitle where type=" + type;
            List<Additiontitle> list = session.createQuery(hql).list();
            session.flush();
            transaction.commit();
            return list;
        } catch (RuntimeException e) {
            transaction.rollback();
            throw new PersistenceException(e);
        }finally {
            session.close();
        }
    }

    public List<Additiontitle> getTitleListByTypeAndPage(int type, int offset, int count) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = getTransation(session);
        try {
            String hql = "from Additiontitle as additiontitle where type=" + type;
            Query query = session.createQuery(hql);
            query.setFirstResult(offset);
            query.setMaxResults(count);
            // setFirstResult和setMaxResults是用来进行分页查询的。
            // 而setFetchSize与分页没有关系，只不过是将符合条件的记录查询出来，然后分批赋值给List
//            query.setFetchSize(count);
            List<Additiontitle> list = query.list();
            System.out.println("offset:" + offset + " count:" + count);
            System.out.println(list.size());
            session.flush();
            transaction.commit();
            return list;
        } catch (RuntimeException e) {
            transaction.rollback();
            throw new PersistenceException(e);
        }finally {
            session.close();
        }
    }

    public List<Additiontitle> getRandomTitleListByType(int count, int type) throws PersistenceException {
        try {
            Set<Integer> set = randomIntegerList(count, type);
            return getTitleListByTitleIds(set);
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    public List<Additiontitle> getTitleListByTitleIds(Set<Integer> titleIds) throws PersistenceException {
            Session session = HibernateUtil.getSession();
        Transaction transaction = getTransation(session);
        try {
            String hql = "from Additiontitle as additiontitle where additiontitle.titleId in (" + formatSet(titleIds) + ")";
            List<Additiontitle> list = session.createQuery(hql).list();
            session.flush();
            transaction.commit();
            return list;
        } catch (RuntimeException e) {
            transaction.rollback();
            throw new PersistenceException(e);
        }finally {
            session.close();
        }
    }

    public Long getTotalTitleSizeByType(int type) throws PersistenceException {
            Session session = HibernateUtil.getSession();
        Transaction transaction = getTransation(session);
        try {
            String hql = "select count(*) from Additiontitle as additiontitle where type=" + type;
            Long count = (Long) session.createQuery(hql).uniqueResult();
            session.flush();
            transaction.commit();
            return count;
        } catch (RuntimeException e) {
            transaction.rollback();
            throw new PersistenceException(e);
        }finally {
            session.close();
        }
    }

    public Set<Integer> randomIntegerList(int count, int type) throws PersistenceException {
            Session session = HibernateUtil.getSession();
        Transaction transaction = getTransation(session);
        try {
            String hql = "select additiontitle.titleId from Additiontitle as additiontitle where type=" + type;
            List<Integer> list = session.createQuery(hql).list();
            session.flush();
            transaction.commit();
            return listToSet(list, count);
        } catch (RuntimeException e) {
            transaction.rollback();
            throw new PersistenceException(e);
        }finally {
            session.close();
        }
    }

    public String formatSet(Set set) throws PersistenceException{
        return formatSet(set, ",");
    }

    public String formatSet(Set set, String split) throws PersistenceException {
        StringBuilder sb = new StringBuilder();
        Iterator values = set.iterator();
        while (values.hasNext()) {
            sb.append(split + values.next());
        }
        return sb.length() > 0 ? sb.subSequence(1, sb.length()).toString() : "";
    }

    public Set parseString(String formatString) throws PersistenceException{
        return parseString(formatString, ",");
    }

    public Set parseString(String formatString, String split) throws PersistenceException {
        String[] strIds = formatString.split(split);
        if (strIds == null) throw new PersistenceException();
        Set<Integer> integerIds = new HashSet<Integer>();
        for (int i = 0;i < strIds.length;i++) {
            integerIds.add(Integer.parseInt(strIds[i]));
        }
        return integerIds;
    }

    /**
     * 从List中随机选取count个Id，以Set形式返回
     * @param list
     * @param count
     * @return
     */
    private Set<Integer> listToSet(List<Integer> list, int count) {
        Set<Integer> set = new HashSet<Integer>();
        if (list.size() <= count) {
            for (Integer integer:list) {
                set.add(integer);
            }
        }else {
            while (set.size() < count) {
                set.add(list.get(((int) (Math.random()*list.size()))));
            }
        }
        return set;
    }
}
