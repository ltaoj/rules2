package com.csu.rules.web;

import com.csu.rules.domain.Clazz;
import com.csu.rules.domain.College;
import com.csu.rules.domain.Major;
import com.csu.rules.exception.CatchServiceException;
import com.csu.rules.exception.SchoolServiceException;
import com.csu.rules.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by ltaoj on 17-7-4.
 */
@Controller
@RequestMapping("/school")
public class SchoolActionBean extends AbstractActionBean {

    private SchoolService schoolService;

    @Autowired
    public SchoolActionBean(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @RequestMapping(value = "/collegeList", method = RequestMethod.GET)
    public List<College> getAllCollege() {
        try {
            return schoolService.getCollegeList();
        }catch (SchoolServiceException se) {
            throw new CatchServiceException(se);
        }
    }

    @RequestMapping(value = "/majorList", method = RequestMethod.POST, consumes = "application/json")
    public List<Major> getMajorList(@RequestBody College college) {
        try {
            return schoolService.getMajorList(college);
        }catch (SchoolServiceException se) {
            throw new CatchServiceException(se);
        }
    }

    @RequestMapping(value = "clazzList", method = RequestMethod.POST, consumes = "application/json")
    public List<Clazz> getClazzList(@RequestBody Major major) {
        try {
            return schoolService.getClazzList(major);
        }catch (SchoolServiceException se) {
            throw new CatchServiceException(se);
        }
    }
}
