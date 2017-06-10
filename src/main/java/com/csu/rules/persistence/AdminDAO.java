package com.csu.rules.persistence;

import com.csu.rules.domain.Admin;

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
    Admin Login(Admin admin);
}
