package com.csu.rules.web;

import com.csu.rules.domain.Clazz;
import com.csu.rules.domain.College;
import com.csu.rules.domain.Major;
import com.csu.rules.exception.CatchServiceException;
import com.csu.rules.exception.SchoolServiceException;
import com.csu.rules.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<College>> getAllCollege() {
        try {
            return new ResponseEntity<List<College>>(schoolService.getCollegeList(), HttpStatus.OK);
        }catch (SchoolServiceException se) {
            throw new CatchServiceException(se);
        }
    }

    @RequestMapping(value = "/majorList", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<List<Major>> getMajorList(@RequestBody College college) {
        try {
            return new ResponseEntity<List<Major>>(schoolService.getMajorList(college),HttpStatus.OK);
        }catch (SchoolServiceException se) {
            throw new CatchServiceException(se);
        }
    }

    @RequestMapping(value = "clazzList", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<List<Clazz>> getClazzList(@RequestBody Major major) {
        try {
            return new ResponseEntity<List<Clazz>>(schoolService.getClazzList(major),HttpStatus.OK);
        }catch (SchoolServiceException se) {
            throw new CatchServiceException(se);
        }
    }
}
