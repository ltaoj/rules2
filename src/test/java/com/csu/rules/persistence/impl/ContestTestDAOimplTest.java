package com.csu.rules.persistence.impl;

import com.csu.rules.domain.Account;
import com.csu.rules.domain.Contestregistion;
import com.csu.rules.domain.Testinfo;
import com.csu.rules.persistence.ContestTestDAO;
import com.csu.rules.service.impl.ContestServiceimpl;
import com.csu.rules.utils.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import java.util.List;

/**
 * Created by GF on 2017/6/8.
 */
public class ContestTestDAOimplTest {
    private Session session= HibernateUtil.getSession();

    @Test
    //数据库可查看是否报名
    public void testRegisterContest(){
        ContestTestDAO contestRegistionDAO=new ContestTestDAOimpl();
        ContestServiceimpl contestServiceimpl=new ContestServiceimpl(contestRegistionDAO);
        Account account=(Account)session.load(Account.class,new Long(3903150332L));
        Testinfo testInfo=(Testinfo)session.load(Testinfo.class,new Integer(3));
        Contestregistion contestregistion=contestServiceimpl.registContest(account,testInfo);
    }

    @Test
    public void testIsRegisted(){
        ContestTestDAO contestRegistionDAO=new ContestTestDAOimpl();
        ContestServiceimpl contestServiceimpl=new ContestServiceimpl(contestRegistionDAO);
        Account account=(Account)session.load(Account.class,new Long(3903150332L));
        Testinfo testInfo=(Testinfo)session.load(Testinfo.class,new Integer(3));
        Contestregistion contestregistion=contestServiceimpl.isRegistedContest(account,testInfo);
        System.out.print(contestregistion.getStudentId()+" "+contestregistion.getStatus());
    }

    @Test
    public void testContestList(){
        ContestTestDAO contestRegistionDAO=new ContestTestDAOimpl();
        ContestServiceimpl contestServiceimpl=new ContestServiceimpl(contestRegistionDAO);
        Testinfo testInfo=(Testinfo)session.load(Testinfo.class,new Integer(3));
        List<Contestregistion> contestregistionList=contestServiceimpl.getContestRegistionList(testInfo);
        for(int i=0;i<contestregistionList.size();i++){
            System.out.print(contestregistionList.get(i).getStudentId()+" ");
        }
    }

    @Test
    public void testChangeStatusBegin(){
        ContestTestDAO contestRegistionDAO=new ContestTestDAOimpl();
        ContestServiceimpl contestServiceimpl=new ContestServiceimpl(contestRegistionDAO);
        Account account=(Account)session.load(Account.class,new Long(3903150332L));
        Testinfo testInfo=(Testinfo)session.load(Testinfo.class,new Integer(3));
        Criteria criteria=session.createCriteria(Contestregistion.class);
        criteria.add(Restrictions.eq("studentId",account.getStudentId()));
        criteria.add(Restrictions.eq("testId",testInfo.getTestId()));
        List list=criteria.list();
        Contestregistion contestRegistion=(Contestregistion) list.get(0);
        Contestregistion contest=contestServiceimpl.changeContestStatusBegin(contestRegistion,testInfo);
        if(contest!=null){
            System.out.print("已改变");
        }
    }

    @Test
    public void testChangeStatusEnd(){
        ContestTestDAO contestRegistionDAO=new ContestTestDAOimpl();
        ContestServiceimpl contestServiceimpl=new ContestServiceimpl(contestRegistionDAO);
        Account account=(Account)session.load(Account.class,new Long(3903150332L));
        Testinfo testInfo=(Testinfo)session.load(Testinfo.class,new Integer(3));
        Criteria criteria=session.createCriteria(Contestregistion.class);
        criteria.add(Restrictions.eq("studentId",account.getStudentId()));
        criteria.add(Restrictions.eq("testId",testInfo.getTestId()));
        List list=criteria.list();
        Contestregistion contestRegistion=(Contestregistion) list.get(0);
        Contestregistion contest=contestServiceimpl.changeContestStatusEnd(contestRegistion,testInfo);
        if(contest!=null){
            System.out.print("已改变");
        }
    }

    @Test
    public void testContsetInfo(){
        ContestTestDAO contestRegistionDAO=new ContestTestDAOimpl();
        ContestServiceimpl contestServiceimpl=new ContestServiceimpl(contestRegistionDAO);
        List<Testinfo> contestInfoList=contestServiceimpl.getContestInfoList();
        System.out.print(contestInfoList.get(0).getName());
    }
}
