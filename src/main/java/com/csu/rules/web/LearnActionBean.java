package com.csu.rules.web;

import com.csu.rules.domain.Clockin;
import com.csu.rules.domain.Result;
import com.csu.rules.exception.CatchServiceException;
import com.csu.rules.exception.LearnServiceException;
import com.csu.rules.service.LearnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;

/**
 * Created by ltaoj on 17-6-12.
 */
@Controller
@RequestMapping(value = "/learn")
public class LearnActionBean extends AbstractActionBean{
    private LearnService learnService;
    @Autowired
    public LearnActionBean(LearnService learnService) {
        this.learnService = learnService;
    }
    @RequestMapping(value = "/clock", method = RequestMethod.GET)
    public String showClockForm() {
        return "clockForm";
    }

    @RequestMapping(value = "/clock", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Result> clock(@RequestBody Clockin clockin) {
        try {
            learnService.insertClock(clockin);
            return new ResponseEntity<Result>(new Result(Result.RESULT_SUCCESS, clockin), HttpStatus.OK);
        }catch (LearnServiceException le) {
            throw new CatchServiceException(le);
        }
    }
    // 用于网页测试，
    @RequestMapping(value = "/clocktest", method = RequestMethod.POST)
    public ResponseEntity<Result> clocktest(
            @RequestParam(value = "studentId") long studentId,
            @RequestParam(value = "duration") int duration,
            @RequestParam(value = "titleNum") int titleNum,
            @RequestParam(value = "clockDay") String clockDay,
            @RequestParam(value = "comment") String comment
            ) {
        try {
            Clockin clockin = new Clockin();
            clockin.setStudentId(studentId);
            clockin.setDuration(duration);
            clockin.setTitleNum(titleNum);
            clockin.setClockDay(Timestamp.valueOf(clockDay));
            clockin.setComment(comment);
            learnService.insertClock(clockin);
            return new ResponseEntity<Result>(new Result(Result.RESULT_SUCCESS), HttpStatus.OK);
        }catch (LearnServiceException le) {
            throw new CatchServiceException(le);
        }
    }
}
