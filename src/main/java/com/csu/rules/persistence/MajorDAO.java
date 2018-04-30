package com.csu.rules.persistence;

import com.csu.rules.domain.Major;
import com.csu.rules.exception.PersistenceException;

import java.util.List;

/**
 * Created by ltaoj on 17-7-4.
 */
public interface MajorDAO {
    /**
     * 插入专业
     * @param major
     * @throws PersistenceException
     */
    void insertMajor(Major major) throws PersistenceException;

    /**
     * 获取专业列表通过学院编号
     * @param collegeId
     * @return
     * @throws PersistenceException
     */
    List<Major> getMajorListByCollegeId(int collegeId) throws PersistenceException;

    /**
     * 获取专业列表通过学院名称
     * @param name
     * @return
     * @throws PersistenceException
     */
    List<Major> getMajorListByName(String name) throws PersistenceException;
}
