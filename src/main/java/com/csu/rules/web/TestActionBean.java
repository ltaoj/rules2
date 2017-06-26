package com.csu.rules.web;

import com.csu.rules.domain.*;
import com.csu.rules.exception.CatchServiceException;
import com.csu.rules.exception.TestServiceException;
import com.csu.rules.exception.TitleServiceException;
import com.csu.rules.service.LearnService;
import com.csu.rules.service.TestService;
import com.csu.rules.service.TitleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by GF on 2017/6/12.
 */
@Controller
@RequestMapping(value = {"/test"})
public class TestActionBean extends AbstractActionBean {
    private TestService testService;
    private TitleService titleService;

    @Autowired
    public TestActionBean(TestService testService, TitleService titleService) {
        this.testService = testService;
        this.titleService = titleService;
    }

    //获取考试信息
    @RequestMapping(value = "/getTestInfo", method = RequestMethod.GET)
    public ResponseEntity<Testinfo> getTestInfo() {
        try {
            Testinfo testinfo = testService.getTestInfoList().get(0);
            return new ResponseEntity<Testinfo>(testinfo, HttpStatus.OK);
        } catch (TestServiceException te) {
            throw new CatchServiceException(te);
        }
    }

    //获取竞赛信息
    @RequestMapping(value = "/getContestInfo", method = RequestMethod.GET)
    public ResponseEntity<Testinfo> getContestInfo() {
        try {
            Testinfo testinfo = testService.getContestInfoList().get(0);
            return new ResponseEntity<Testinfo>(testinfo, HttpStatus.OK);
        } catch (TestServiceException te) {
            throw new CatchServiceException(te);
        }
    }

    //开始考试 插入考试时间信息 判断剩余时间
    @RequestMapping(value = "/startTest", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<List<Title>> getTestTitle(@RequestBody Testrecord testrecord) {
        try {
            Testrecord testrecord1 = testService.getTestRecord(testrecord);
            if (testrecord1 == null) {
                testrecord.setStartTime(new Timestamp(System.currentTimeMillis()));
                testService.insertTestRecord(testrecord);
            }
            List<Title> testTitleList = titleService.getTitleListByRandom(2);
            return new ResponseEntity<List<Title>>(testTitleList, HttpStatus.OK);
        } catch (TestServiceException te) {
            throw new CatchServiceException(te);
        } catch (TitleServiceException e) {
            throw new CatchServiceException(e);
        }
    }

    /**
     * 根据有无成绩查看是否考过试
     * 若中途退出考试 查看开始考试的时间
     * 显示考试成绩
     *
     * @param testrecord
     * @return
     */
    @RequestMapping(value = "/testRecordInfo", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Testrecord> getTestRecord(@RequestBody Testrecord testrecord) {
        try {
            Testrecord testrecord1 = testService.getTestRecord(testrecord);
            return new ResponseEntity<Testrecord>(testrecord1, HttpStatus.OK);
        } catch (TestServiceException te) {
            throw new CatchServiceException(te);
        }
    }

    //提交考试 获取考试成绩 更新testRecord
    @RequestMapping(value = "/submitTest", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Testrecord> submitTest(@RequestBody RecordTitles recordTitles) {
        try {
            int score = titleService.getTitlePageScore(recordTitles.getTitleList());
            Testrecord testrecord = recordTitles.getTestrecord();
            testrecord.setSubmitTime(new Timestamp(System.currentTimeMillis()));
            testrecord.setScore(score);
            testService.updateTestRecord(testrecord);
            Testrecord testrecord1 = testService.getTestRecord(testrecord);
            return new ResponseEntity<Testrecord>(testrecord1, HttpStatus.OK);
        } catch (TitleServiceException te) {
            throw new CatchServiceException(te);
        } catch (TestServiceException e) {
            throw new CatchServiceException(e);
        }
    }

    //获取竞赛排名
    @RequestMapping(value = "/getContsetRank", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Integer> getContsetRank(@RequestBody Testrecord testrecord) {
        try {
            int count = testService.getContestRank(testrecord);
            return new ResponseEntity<Integer>(count, HttpStatus.OK);
        } catch (TestServiceException e) {
            throw new CatchServiceException(e);
        }
    }

    //是否报名竞赛
    @RequestMapping(value = "/isRegisted", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Contestregistion> isRegisted(@RequestBody Contestregistion contestregistion) {
        try {
            Contestregistion contestregistion1 = testService.isRegistedContest(contestregistion);
            return new ResponseEntity<Contestregistion>(contestregistion1, HttpStatus.OK);
        } catch (TestServiceException e) {
            throw new CatchServiceException(e);
        }
    }

    //报名竞赛
    @RequestMapping(value = "/registContest", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Result> registContest(@RequestBody Contestregistion contestregistion) {
        try {
            testService.registContest(contestregistion);
            return new ResponseEntity<Result>(new Result(Result.RESULT_SUCCESS, contestregistion), HttpStatus.OK);
        } catch (TestServiceException e) {
            throw new CatchServiceException(e);
        }
    }

    //获取竞赛状态
    @RequestMapping(value = "/getContestStatus", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Contestregistion> getContestStatus(@RequestBody Contestregistion contestregistion) {
        try {
            Contestregistion contestregistion2 = testService.isRegistedContest(contestregistion);
            if(contestregistion2.getStudentId()==contestregistion.getStudentId()) {
                Contestregistion contestregistion1 = testService.changeContestStatus(contestregistion);
                return new ResponseEntity<Contestregistion>(contestregistion1, HttpStatus.OK);
            }else{
                Contestregistion contestregistion1=new Contestregistion();
                return new ResponseEntity<Contestregistion>(contestregistion1, HttpStatus.OK);
            }
        } catch (TestServiceException e) {
            throw new CatchServiceException(e);
        }
    }

    //开始竞赛 插入时间信息 判断剩余时间(有问题)
//    @RequestMapping(value="/startContest",method=RequestMethod.POST,consumes="application/json")
//    public ResponseEntity<List<Title>> getContestTitle(@RequestBody Testrecord testrecord){
//        try{
//            Testrecord testrecord1=testService.getTestRecord(testrecord);
//            if (testrecord1==null) {
//                testService.insertTestRecord(testrecord);
//            }
//            List<Title> contestTitleList=titleService.getTitleListByRandom(2);
//            return new ResponseEntity<List<Title>>(contestTitleList,HttpStatus.OK);
//        }catch (TestServiceException te){
//            throw new CatchServiceException(te);
//        }catch (TitleServiceException e){
//            throw new CatchServiceException(e);
//        }
//    }

    /**************************************************管理员****************************************************/
    //插入考试信息
    @RequestMapping(value = "/insertTest", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Result> insertTest(@RequestBody Testinfo testinfo) {
        try {
            testService.insertTestInfo(testinfo);
            return new ResponseEntity<Result>(new Result(Result.RESULT_SUCCESS, testinfo), HttpStatus.OK);
        } catch (TestServiceException e) {
            throw new CatchServiceException(e);
        }
    }

    //删除考试信息
    @RequestMapping(value = "/deleteTest", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Result> deleteTest(@RequestBody Integer testId) {
        try {
            testService.deleteTestInfo(testId);
            return new ResponseEntity<Result>(new Result(Result.RESULT_SUCCESS, testId), HttpStatus.OK);
        } catch (TestServiceException e) {
            throw new CatchServiceException(e);
        }
    }

    //修改考试信息
    @RequestMapping(value = "/updateTest", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Result> updateTest(@RequestBody Testinfo testinfo) {
        try {
            testService.updateTestInfo(testinfo);
            return new ResponseEntity<Result>(new Result(Result.RESULT_SUCCESS, testinfo), HttpStatus.OK);
        } catch (TestServiceException e) {
            throw new CatchServiceException(e);
        }
    }

    //查看考试信息
    @RequestMapping(value = "/getTest", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Testinfo> insertTest(@RequestBody Integer testId) {
        try {
            Testinfo testinfo = testService.getTestInfo(testId);
            return new ResponseEntity<Testinfo>(testinfo, HttpStatus.OK);
        } catch (TestServiceException e) {
            throw new CatchServiceException(e);
        }
    }

    //查看学生考试成绩
    @RequestMapping(value = "/getAllTestRecord", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<List<Testrecord>> getAllTestRecord(@RequestBody Integer testId) {
        try {
            List<Testrecord> recordList = testService.getTestRecordList(testId);
            return new ResponseEntity<List<Testrecord>>(recordList, HttpStatus.OK);
        } catch (TestServiceException e) {
            throw new CatchServiceException(e);
        }
    }

    //管理员发布竞赛题目
    @RequestMapping(value = "/publishContestTitle", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<List<Title>> getAllTestRecord(@RequestBody Set<Integer> set) {
        try {
            List<Title> contestTitleList = titleService.getTitleListByTitleIds(set);
            return new ResponseEntity<List<Title>>(contestTitleList, HttpStatus.OK);
        } catch (TitleServiceException e) {
            throw new CatchServiceException(e);
        }
    }
}
