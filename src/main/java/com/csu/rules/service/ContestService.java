package com.csu.rules.service;

import com.csu.rules.domain.Account;
import com.csu.rules.domain.Contestregistion;
import com.csu.rules.domain.Testinfo;

import java.util.List;

/**
 * Created by GF on 2017/6/9.
 */
public interface ContestService {
    /**
     *报名竞赛 根据竞赛的开始时间判断是否可以报名
     */
    void registContest(Account account, Testinfo testinfo);
    /**
     *查看是否已经报名竞赛
     * */
    Contestregistion isRegistedContest(Account account, Testinfo testInfo);
    /**
     * 根据竞赛Id查看已经报名竞赛的人
     * */
    List<Contestregistion> getContestRegistionList(Testinfo testInfo);
    /**
     * 根据竞赛startTime和endTime，依据当前系统时间改变竞赛的状态
     * status:0.未开始
     * status:1.开始
     * status;2.已结束
     * */
    Contestregistion changeContestStatus(Contestregistion contestregistion,Testinfo testinfo);

    List<Testinfo> getContestInfoList();

}
