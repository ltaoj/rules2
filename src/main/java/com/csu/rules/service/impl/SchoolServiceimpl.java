package com.csu.rules.service.impl;

import com.csu.rules.domain.Clazz;
import com.csu.rules.domain.College;
import com.csu.rules.domain.Major;
import com.csu.rules.exception.PersistenceException;
import com.csu.rules.exception.SchoolServiceException;
import com.csu.rules.persistence.ClazzDAO;
import com.csu.rules.persistence.CollegeDAO;
import com.csu.rules.persistence.MajorDAO;
import com.csu.rules.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ltaoj on 17-7-4.
 */
@Service
public class SchoolServiceimpl implements SchoolService {
    private CollegeDAO collegeDAO;
    private MajorDAO majorDAO;
    private ClazzDAO clazzDAO;

    @Autowired
    public SchoolServiceimpl(CollegeDAO collegeDAO, MajorDAO majorDAO, ClazzDAO clazzDAO) {
        this.collegeDAO = collegeDAO;
        this.majorDAO = majorDAO;
        this.clazzDAO = clazzDAO;
    }

    public List<College> getCollegeList() throws SchoolServiceException {
        try {
            return collegeDAO.getCollegeList();
        }catch (PersistenceException pe) {
            SchoolServiceException se = new SchoolServiceException(pe);
            se.setErrorCode(50);
            throw se;
        }
    }

    public List<Major> getMajorList(College college) throws SchoolServiceException {
        try {
            SchoolServiceException se = new SchoolServiceException();
            if (college == null) {
                se.setErrorCode(70);
                throw se;
            }

            if (college.getCollegeId() != 0)
                return majorDAO.getMajorListByCollegeId(college.getCollegeId());
            else if (college.getName() != null && !college.getName().equals(""))
                return majorDAO.getMajorListByName(college.getName());

            se.setErrorCode(70);
            throw se;
         }catch (PersistenceException pe) {
            SchoolServiceException se = new SchoolServiceException(pe);
            se.setErrorCode(50);
            throw se;
        }
    }

    public List<Clazz> getClazzList(Major major) throws SchoolServiceException {
        try {
            SchoolServiceException se = new SchoolServiceException();
            if (major == null) {
                se.setErrorCode(71);
                throw se;
            }

            if (major.getMajorId() != 0)
                return clazzDAO.getClazzByMajorId(major.getMajorId());
            else if (major.getName() != null && !major.getName().equals(""))
                return clazzDAO.getClazzByName(major.getName());

            se.setErrorCode(71);
            throw se;
        }catch (PersistenceException pe) {
            SchoolServiceException se = new SchoolServiceException(pe);
            se.setErrorCode(50);
            throw se;
        }
    }
}
