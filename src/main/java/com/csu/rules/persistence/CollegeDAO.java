package com.csu.rules.persistence;

import com.csu.rules.domain.College;
import com.csu.rules.exception.PersistenceException;

import java.util.List;

/**
 * Created by ltaoj on 17-7-4.
 */
public interface CollegeDAO {
    /**
     * 插入学院
     * @param college
     * @throws PersistenceException
     */
    void insertCollege(College college) throws PersistenceException;

    /**
     * 获取学院列表
     * @return
     * @throws PersistenceException
     */
    List<College> getCollegeList() throws PersistenceException;
}
