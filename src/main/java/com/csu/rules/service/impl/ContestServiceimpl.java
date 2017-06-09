package com.csu.rules.service.impl;

import com.csu.rules.domain.Account;
import com.csu.rules.domain.Contestregistion;
import com.csu.rules.domain.Testinfo;
import com.csu.rules.persistence.ContestTestDAO;
import com.csu.rules.service.ContestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by GF on 2017/6/9.
 */
@Service
public class ContestServiceimpl implements ContestService{
    private ContestTestDAO contestTestDAO;
    @Autowired
    public ContestServiceimpl(ContestTestDAO contestTestDAO){
        this.contestTestDAO=contestTestDAO;
    }

    /**
     *报名竞赛 根据竞赛的开始时间判断是否可以报名
     * 返回对象为空则未报名成功
     */
    public Contestregistion registContest(Account account, Testinfo testinfo) {
        Contestregistion contestregistion=new Contestregistion();
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        Timestamp startTime=testinfo.getStartTime();
        if(currentTime.before(startTime)){
            contestregistion=contestTestDAO.registContest(account,testinfo);
        }
        return contestregistion;
    }

    /**
     *查看是否已经报名竞赛
     * 返回对象不为空则已经报名
     * */
    public Contestregistion isRegistedContest(Account account, Testinfo testInfo) {
        Contestregistion contestregistion=new Contestregistion();
        contestregistion=contestTestDAO.isRegistedContest(account,testInfo);
        return contestregistion;
    }
    /**
     * 根据竞赛Id查看已经报名竞赛的人
     * */
    public List<Contestregistion> getContestRegistionList(Testinfo testInfo) {
        List<Contestregistion> contestRegistionList=contestTestDAO.getContestRegistionList(testInfo);
        return contestRegistionList;
    }
    /**
     * 根据竞赛startTime和endTime，依据当前系统时间改变竞赛的状态为开始
     * status:0.未开始
     * status:1.开始
     * status;2.已结束
     * */
    public Contestregistion changeContestStatusBegin(Contestregistion contestregistion,Testinfo testinfo) {
        Contestregistion contest=new Contestregistion();
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        Timestamp startTime=testinfo.getStartTime();
        Timestamp endTime=testinfo.getEndTime();
        if(currentTime.before(endTime)&&currentTime.after(startTime)){
            contest=contestTestDAO.changeContestStatusBegin(contestregistion);
        }
        return contest;
    }
    /**
     * 根据竞赛startTime和endTime，依据当前系统时间改变竞赛的状态为结束
     * status:0.未开始
     * status:1.开始
     * status;2.已结束
     * */
    public Contestregistion changeContestStatusEnd(Contestregistion contestregistion,Testinfo testinfo) {
        Contestregistion contest=new Contestregistion();
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        Timestamp endTime=testinfo.getEndTime();
        if(currentTime.after(endTime)){
            contest=contestTestDAO.changeContestStatusEnd(contestregistion);
        }
        return contest;
    }

    //获取竞赛相关信息
    public List<Testinfo> getContestInfoList() {
        List<Testinfo> contestInfoList=contestTestDAO.getContestInfo();
        return contestInfoList;
    }
}
