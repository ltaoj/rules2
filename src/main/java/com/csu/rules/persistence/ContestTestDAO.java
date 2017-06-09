package com.csu.rules.persistence;



import com.csu.rules.domain.Account;
import com.csu.rules.domain.Contestregistion;
import com.csu.rules.domain.Testinfo;

import javax.persistence.PersistenceException;
import java.util.List;

/**
 * Created by GF on 2017/6/8.
 */
public interface ContestTestDAO {
    //报名竞赛
    Contestregistion registContest(Account account, Testinfo testInfo) throws PersistenceException;
    //查看是否已经报名
    Contestregistion isRegistedContest(Account account, Testinfo testInfo)throws PersistenceException;
    //根据竞赛id获取已经报名的学生
    List<Contestregistion> getContestRegistionList(Testinfo testInfo)throws PersistenceException;
    //改变竞赛当前状态status为开始
    Contestregistion changeContestStatusBegin(Contestregistion contestregistion)throws PersistenceException;
    //改变竞赛当前状态status为结束
    Contestregistion changeContestStatusEnd(Contestregistion contestregistion)throws PersistenceException;
    //返回竞赛相关信息
    List<Testinfo> getContestInfo() throws PersistenceException;
    //获取考试信息
    List<Testinfo> getTestInfo() throws PersistenceException;
}
