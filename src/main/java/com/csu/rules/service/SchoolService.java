package com.csu.rules.service;

import com.csu.rules.domain.Clazz;
import com.csu.rules.domain.College;
import com.csu.rules.domain.Major;
import com.csu.rules.exception.SchoolServiceException;

import java.util.List;

/**
 * Created by ltaoj on 17-7-4.
 */
public interface SchoolService {
    /**
     * 获取所有学院
     * @return
     * @throws SchoolServiceException
     * 50.数据持久化异常
     */
    List<College> getCollegeList() throws SchoolServiceException;

    /**
     * 获取专业列表
     * @param college
     * @return
     * @throws SchoolServiceException
     * 70.College字段异常
     * 50.数据持久化异常
     */
    List<Major> getMajorList(College college) throws SchoolServiceException;

    /**
     * 获取班级列表
     * @param major
     * @return
     * @throws SchoolServiceException
     * 71.Major字段异常
     * 50.数据持久化异常
     */
    List<Clazz> getClazzList(Major major) throws SchoolServiceException;
}
