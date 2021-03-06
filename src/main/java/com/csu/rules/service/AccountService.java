package com.csu.rules.service;

import com.csu.rules.domain.*;
import com.csu.rules.domain.Feedback;
import com.csu.rules.exception.AccountServiceException;
import com.csu.rules.persistence.*;
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
    Account login(String studentId, String password) throws AccountServiceException;

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
    void submitProposals(String studentId, String mail, String content) throws AccountServiceException;

    /**
     * 用户兑换礼品
     *
     * @param integral
     * @throws AccountServiceException
     */
    void exchange(Integral integral) throws AccountServiceException;

    /**
     * 添加管理员
     * @param admin
     * @throws AccountServiceException
     */
    void insertAdmin(Admin admin) throws AccountServiceException;

    /**
     * 添加用户信息
     * @param account
     * @throws AccountServiceException
     */
    void insertUser(Account account) throws AccountServiceException;

   /**
     * 批量添加用户信息
     * 2018/04/22 添加
     * @param account
     * @throws AccountServiceException
     */
    void insertUserList(List<Account> accountList) throws AccountServiceException;
    
    /**
     * 添加用户账号
     * @param signon
     * @throws AccountServiceException
     */
    void insertSignon(Signon signon) throws AccountServiceException;

    /**
     * 添加反馈信息
     * @param feedback
     * @throws AccountServiceException
     */
    void insertFeedback(Feedback feedback) throws AccountServiceException;

    /**
     * 返回所有反馈信息
     * @return
     * @throws AccountServiceException
     */
    List<Feedback> getFeedbackList() throws AccountServiceException;

    /**
     * 通过条件获取学生
     * @param college
     * @return
     * @throws AccountServiceException
     */
    List<Account> getAccountListByCondition(String college)throws AccountServiceException;

    /**
     * 根据 账户Id获取密码信息
     * @param accountId
     * @return
     */
    Signon getSignon(String accountId) throws AccountServiceException;

    /**
     * 更新密码信息
     * @param signon
     */
    void updateSignon(Signon signon) throws AccountServiceException;

    /**
     * 更新用户信息
     * @param account
     */
    void updateUser(Account account) throws AccountServiceException;
}

