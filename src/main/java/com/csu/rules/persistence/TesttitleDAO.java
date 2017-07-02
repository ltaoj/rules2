package com.csu.rules.persistence;

import com.csu.rules.domain.Account;
import com.csu.rules.domain.Testinfo;
import com.csu.rules.domain.Testtitle;
import com.csu.rules.exception.PersistenceException;
import org.aspectj.weaver.ast.Test;

/**
 * Created by ltaoj on 17-7-2.
 */
public interface TesttitleDAO {
    /**
     * @param testtitle
     * @throws PersistenceException
     * 插入学生考试试题
     */
    void insertTesttitle (Testtitle testtitle) throws PersistenceException;

    /**
     * @param studentId
     * @param testId
     * @return
     * @throws PersistenceException
     * 根据学号和考试id查找关联试题
     */
    Testtitle getTesttitle (long studentId, int testId) throws PersistenceException;

    /**
     * @param testtitle
     * @return
     * @throws PersistenceException
     * 查找学生考试关联试题
     */
    Testtitle getTesttitle (Testtitle testtitle) throws PersistenceException;

    /**
     * @param account
     * @param testinfo
     * @return
     * @throws PersistenceException
     * 根据用户和考试信息查找关联试题
     */
    Testtitle getTesttitle (Account account, Testinfo testinfo) throws PersistenceException;

    /**
     * @param studentId
     * @param testId
     * @throws PersistenceException
     * 根据学号和考试id删除关联试题记录
     */
    void deleteTesttitle (long studentId, int testId) throws PersistenceException;

    /**
     * @param testtitle
     * @throws PersistenceException
     * 删除关联试题记录
     */
    void deleteTesttitle (Testtitle testtitle) throws PersistenceException;

    /**
     * @param account
     * @param testinfo
     * @throws PersistenceException
     * 根据用户和考试信息删除关联试题记录
     */
    void deleteTesttitle (Account account, Testinfo testinfo) throws PersistenceException;
}
