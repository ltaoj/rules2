package com.csu.rules.persistence;

import com.csu.rules.domain.Admin;
import com.csu.rules.exception.PersistenceException;

/**
 * Created by CMM on 2017/6/10.
 */
public interface AdminDAO {
    /**
     * 管理员登录
     *
     * @param admin
     * @return
     */
    Admin Login(Admin admin) throws PersistenceException;

    /**
     * 添加管理员
     * @param admin
     * @throws PersistenceException
     */
    void insertAdmin(Admin admin) throws PersistenceException;


}
