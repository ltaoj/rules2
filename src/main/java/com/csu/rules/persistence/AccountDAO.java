package com.csu.rules.persistence;

import com.csu.rules.domain.Account;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceException;

/**
 * Created by ltaoj on 17-5-30.
 */

public interface AccountDAO{
    /**
     * @param studentId
     * @param password
     * @return
     * @throws PersistenceException
     */
    Account login(long studentId, String password) throws PersistenceException;
}
