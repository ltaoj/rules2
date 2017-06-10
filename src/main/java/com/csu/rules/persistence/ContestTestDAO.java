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
    /**
     * 报名竞赛
     * status 0.未开始 1.开始 2.已结束
     * @param account
     * @param testInfo
     * @throws PersistenceException
     */
     void registContest(Account account, Testinfo testInfo) throws PersistenceException;

    /**
     * 判断是否已经报名竞赛
     * @param account
     * @param testInfo
     * @return
     * @throws PersistenceException
     */
    Contestregistion isRegistedContest(Account account, Testinfo testInfo)throws PersistenceException;

    /**
     * 根据竞赛id查看已经报名竞赛的人
     * @param testInfo
     * @return
     * @throws PersistenceException
     */
    List<Contestregistion> getContestRegistionList(Testinfo testInfo)throws PersistenceException;

    /**
     * 改变竞赛考试的状态status为开始 status:0.未开始 1.开始 2.已结束
     * @param contestregistion
     * @return
     * @throws PersistenceException
     */
    Contestregistion changeContestStatusBegin(Contestregistion contestregistion)throws PersistenceException;

    /**
     * 改变竞赛考试的状态status为结束 status:0.未开始 1.开始 2.已结束
     * @param contestregistion
     * @return
     * @throws PersistenceException
     */
    Contestregistion changeContestStatusEnd(Contestregistion contestregistion)throws PersistenceException;

    /**
     * 返回多个竞赛相关信息 type:0 考试 1 竞赛
     * @return
     * @throws PersistenceException
     */
    List<Testinfo> getContestInfoList() throws PersistenceException;
}
