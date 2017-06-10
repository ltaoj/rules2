package com.csu.rules.service;

import com.csu.rules.domain.Account;
import com.csu.rules.domain.Contestregistion;
import com.csu.rules.domain.Signon;
import com.csu.rules.domain.Testinfo;
import com.csu.rules.exception.AccountServiceException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ltaoj on 17-5-30.
 */
public interface AccountService {
    /**********************************用户登录************************************************/
    /**
     * @param studentId
     * @param password
     * @return
     * @throws AccountServiceException 业务层登录异常包括
     *                                 0.studentId字段检验异常
     *                                 1.password字段检验异常
     *                                 2.账号密码错误
     *                                 3.持久层失败异常
     */
    Account login(long studentId, String password) throws AccountServiceException;

    /**
     * @param signon
     * @return
     * @throws AccountServiceException 4.signon字段检验异常
     */
    Account login(Signon signon) throws AccountServiceException;
    /**********************************用户登录结束************************************************/

    /**********************************获取用户基本信息********************************************/
    /**
     * 获取用户信息
     *
     * @param account
     * @return
     * @throws AccountServiceException 业务层获取用户信息异常包括：
     *                                 5.用户未登录，尝试查看用户信息
     */
    Account getUserInfo(Account account) throws AccountServiceException;
    /**********************************获取用户基本信息结束****************************************/

    /**********************************用户竞赛信息************************************************/
    /**
     * 报名竞赛 根据竞赛的开始时间判断是否可以报名
     *
     * @param account
     * @param testinfo
     * @return
     */
    Contestregistion registContest(Account account, Testinfo testinfo);

    /**
     * 查看是否已经报名竞赛
     *
     * @param account
     * @param testInfo
     * @return
     */
    Contestregistion isRegistedContest(Account account, Testinfo testInfo);

    /**
     * 根据竞赛startTime和endTime，依据当前系统时间改变竞赛的状态
     * status:0.未开始
     * status:1.开始
     * status;2.已结束
     *
     * @param contestregistion
     * @param testinfo
     * @return
     */
    Contestregistion changeContestStatus(Contestregistion contestregistion, Testinfo testinfo);

    /**
     * 获取竞赛相关考试信息
     *
     * @return
     */
    List<Testinfo> getContestInfoList();
    /**********************************用户竞赛信息结束**********************************************/
}
