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
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.List;
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
            List<Testinfo> testinfoList=testService.getTestInfoList();
            if(testinfoList.size()!=0) {
                Testinfo testinfo = testService.getTestInfoList().get(0);
                return new ResponseEntity<Testinfo>(testinfo, HttpStatus.OK);
            }else{
                return new ResponseEntity<Testinfo>(new Testinfo(), HttpStatus.OK);
            }
        } catch (TestServiceException te) {
            throw new CatchServiceException(te);
        }
    }

    //获取竞赛信息
    @RequestMapping(value = "/getContestInfo", method = RequestMethod.GET)
    public ResponseEntity<Testinfo> getContestInfo() {
        try {
            List<Testinfo> contestinfoList=testService.getContestInfoList();
            if(contestinfoList.size()!=0) {
                Testinfo testinfo = contestinfoList.get(0);
                return new ResponseEntity<Testinfo>(testinfo, HttpStatus.OK);
            }else {
                return new ResponseEntity<Testinfo>(new Testinfo(), HttpStatus.OK);
            }
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
                String formatRandomIds = titleService.getRandomIntegerList(10);
                testrecord.setStartTime(new Timestamp(System.currentTimeMillis()));
                testService.insertTestRecord(testrecord);
                testService.insertTesttitle(testrecord, formatRandomIds);
            }
            Testtitle testtitle = testService.getTesttitleByTestrecord(testrecord);
            List<Title> testTitleList = titleService.getTitleListByFormatString(testtitle.getTitleIds());
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
            if (testrecord1 == null) {
                return new ResponseEntity<Testrecord>(new Testrecord(), HttpStatus.OK);
            }
            return new ResponseEntity<Testrecord>(testrecord1, HttpStatus.OK);
        } catch (TestServiceException te) {
            throw new CatchServiceException(te);
        }
    }

    //插入考试成绩的submitTime 判断考试剩余时间
    @RequestMapping(value = "/insertSubmitTime", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Result> insertSubmitTime(@RequestBody Testrecord testrecord) {
        try {
            Testrecord testrecord1 = testService.getTestRecord(testrecord);
            if(testrecord1==null){
                return new ResponseEntity<Result>(new Result(Result.RESULT_SUCCESS), HttpStatus.OK);
            }else {
                testrecord1.setSubmitTime(new Timestamp(System.currentTimeMillis()));
                testService.updateTestRecord(testrecord1);
                return new ResponseEntity<Result>(new Result(Result.RESULT_SUCCESS), HttpStatus.OK);
            }
        } catch (TestServiceException te) {
            throw new CatchServiceException(te);
        }
    }

    //提交考试 获取考试成绩 更新testRecord
    @RequestMapping(value = "/submitTest", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Testrecord> submitTest(@RequestBody RecordTitles recordTitles) {
        try {
            Testrecord testrecord=testService.getTestRecord(recordTitles.getTestrecord());
            int score = titleService.getTitlePageScore(recordTitles.getTitleList());
            testrecord.setSubmitTime(new Timestamp(System.currentTimeMillis()));
            testrecord.setScore(score);
            testService.updateTestRecord(testrecord);
            return new ResponseEntity<Testrecord>(testrecord, HttpStatus.OK);
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
            if (contestregistion2.getStudentId() == contestregistion.getStudentId()) {
                Contestregistion contestregistion1 = testService.changeContestStatus(contestregistion);
                return new ResponseEntity<Contestregistion>(contestregistion1, HttpStatus.OK);
            } else {
                Contestregistion contestregistion1 = new Contestregistion();
                return new ResponseEntity<Contestregistion>(contestregistion1, HttpStatus.OK);
            }
        } catch (TestServiceException e) {
            throw new CatchServiceException(e);
        }
    }

    //判断竞赛时间
    @RequestMapping(value = "/getContestTime", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Result> getContestTime(@RequestBody Testinfo testinfo) {
        try {
                Testinfo testinfo1=testService.getTestInfo(testinfo.getTestId());
                if(testinfo1.getEndTime().before(new Timestamp(System.currentTimeMillis()))){
                    return new ResponseEntity<Result>(new Result(Result.RESULT_ERROR), HttpStatus.OK);
                }else{
                    return new ResponseEntity<Result>(new Result(Result.RESULT_SUCCESS), HttpStatus.OK);
                }
        } catch (TestServiceException e) {
            throw new CatchServiceException(e);
        }
    }

    //点击X 删除考试成绩信息
    @RequestMapping(value = "/deleteTestRecord", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Result> deleteTestRecord(@RequestBody Testrecord testrecord) {
        try {
            testService.deleteTestRecord(testrecord);
            testService.deleteTestTitle(testrecord);
            return new ResponseEntity<Result>(new Result(Result.RESULT_SUCCESS), HttpStatus.OK);
        } catch (TestServiceException e) {
            throw new CatchServiceException(e);
        }
    }

    //开始竞赛 插入时间信息 返回确定竞赛试题
    @RequestMapping(value="/startContest",method=RequestMethod.POST,consumes="application/json")
    public ResponseEntity<List<Title>> getContestTitle(@RequestBody Testrecord testrecord){
        try {
            Testrecord testrecord1 = testService.getTestRecord(testrecord);
            if (testrecord1 == null) {
                testrecord.setStartTime(new Timestamp(System.currentTimeMillis()));
                testService.insertTestRecord(testrecord);
            }
            Contesttitle contesttitle=testService.getContesttitle(testrecord.getTestId());
            List<Title> contestTitleList = titleService.getTitleListByFormatString(contesttitle.getTitleIds());
            return new ResponseEntity<List<Title>>(contestTitleList, HttpStatus.OK);
        } catch (TestServiceException te) {
            throw new CatchServiceException(te);
        } catch (TitleServiceException e) {
            throw new CatchServiceException(e);
        }
    }

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


    //管理员发布竞赛题目(自己挑选试题)
    @RequestMapping(value = "/insertContestTitle", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Result> insertContestTitle(@RequestBody Contesttitle contesttitle) {
        try {
            testService.insertContesttitle(contesttitle);
            return new ResponseEntity<Result>(new Result(Result.RESULT_SUCCESS), HttpStatus.OK);
        } catch (TestServiceException e) {
            throw new CatchServiceException(e);
        }
    }

    //管理员查看系统提供的随机竞赛试题
    @RequestMapping(value = "/getContestRandomTitle", method = RequestMethod.GET)
    public ResponseEntity<List<Title>> getContestRandomTitle() {
        try {
            List<Title> titleList=titleService.getTitleListByRandom(10);
            return new ResponseEntity<List<Title>>(titleList, HttpStatus.OK);
        } catch (TitleServiceException e) {
            throw new CatchServiceException(e);
        }
    }

    //管理员删除竞赛试题
    @RequestMapping(value = "/deleteContestTitle", method = RequestMethod.POST,consumes = "application/json")
    public ResponseEntity<Result> deleteContestTitle(@RequestBody Testinfo testinfo) {
        try {
            Contesttitle contesttitle=testService.getContesttitle(testinfo);
            if(contesttitle!=null) {
                testService.deleteContesttitle(testinfo);
            }
            return new ResponseEntity<Result>(new Result(Result.RESULT_SUCCESS), HttpStatus.OK);
        } catch (TestServiceException e) {
            throw new CatchServiceException(e);
        }
    }

    //管理员查看竞赛试题
    @RequestMapping(value = "/getContestListTitle", method = RequestMethod.POST,consumes = "application/json")
    public ResponseEntity<List<Title>> getContestListTitle(@RequestBody Testinfo testinfo) {
        try {
            Contesttitle contesttitle=testService.getContesttitle(testinfo);
            List<Title> contestTitleList = titleService.getTitleListByFormatString(contesttitle.getTitleIds());
            return new ResponseEntity<List<Title>>(contestTitleList, HttpStatus.OK);
        } catch (TestServiceException e) {
            throw new CatchServiceException(e);
        }catch (TitleServiceException e){
            throw new CatchServiceException(e);
        }
    }

    @RequestMapping(value = "/recordListByCondition", method = RequestMethod.GET)
    public ResponseEntity<List<Testrecord>> getTestRecordByCondition(
            @RequestParam(value = "clazz", defaultValue = "") String clazz,
            @RequestParam(value = "grade", defaultValue = "0") int grade,
            @RequestParam(value = "major", defaultValue = "") String major,
            @RequestParam(value = "college", defaultValue = "") String college,
            @RequestParam(value = "level", defaultValue = "0") int level) {

        try {
            List<Testrecord> testrecordList = testService.getTestRecordByCondition(clazz, grade, major, college, level);
            return new ResponseEntity<List<Testrecord>>(testrecordList, HttpStatus.OK);
        }catch (TestServiceException te) {
            throw new CatchServiceException(te);
        }
    }
}
