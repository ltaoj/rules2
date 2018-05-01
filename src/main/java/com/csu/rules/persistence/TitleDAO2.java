package com.csu.rules.persistence;

import com.csu.rules.exception.PersistenceException;

import java.util.List;

/**
 * Created by ltaoj on 2018/05/01 14:20.
 *
 * @version : 1.0
 */
public interface TitleDAO2 {

    /**
     * @return
     * @throws PersistenceException
     * 返回题库总题目数
     */
    Long getTotalTitleSize() throws PersistenceException;
}
