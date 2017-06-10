package com.csu.rules.service;

import com.csu.rules.domain.*;
import com.csu.rules.exception.AccountServiceException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ltaoj on 17-5-30.
 */
public interface AccountService {

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

    /**
     * 管理员登录
     *
     * @param admin
     * @return
     * @throws AccountServiceException 业务层登录异常包括
     *                                 6.adminId字段检验异常
     *                                 7.password字段检验异常
     *                                 8.账号密码错误
     */
    Admin login(Admin admin) throws AccountServiceException;

    /**
     * 获取用户信息
     *
     * @param account
     * @return
     * @throws AccountServiceException 业务层获取用户信息异常包括：
     *                                 5.用户未登录，尝试查看用户信息
     */
    Account getUserInfo(Account account) throws AccountServiceException;

    /**
     * 用户提交建议
     *
     * @param studentId
     * @param mail
     * @param content
     * @throws AccountServiceException
     */
    void submitProposals(Long studentId, String mail, String content) throws AccountServiceException;

    /**
     * 用户兑换礼品
     *
     * @param integral
     * @throws AccountServiceException
     */
    void exchange(Integral integral) throws AccountServiceException;
}
