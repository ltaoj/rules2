package com.csu.rules.service.impl;

import com.csu.rules.domain.*;
import com.csu.rules.exception.TestServiceException;
import com.csu.rules.persistence.*;
import com.csu.rules.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by GF on 2017/6/9.
 */
@Service
public class TestServiceimpl implements TestService {
    private ContestTestDAO contestTestDAO;
    private TestDAO testDAO;
    private TestRecordDAO testRecordDAO;
    private TesttitleDAO testtitleDAO;
    private ContestTitleDAO contestTitleDAO;

    @Autowired
    public TestServiceimpl(ContestTestDAO contestTestDAO, TestDAO testDAO, TestRecordDAO testRecordDAO,
                           TesttitleDAO testtitleDAO, ContestTitleDAO contestTitleDAO) {
        this.contestTestDAO = contestTestDAO;
        this.testDAO = testDAO;
        this.testRecordDAO = testRecordDAO;
        this.testtitleDAO = testtitleDAO;
        this.contestTitleDAO = contestTitleDAO;
    }

    public void registContest(Contestregistion contestregistion) throws TestServiceException {
        try {
            contestTestDAO.registContest(contestregistion);
        } catch (PersistenceException e) {
            TestServiceException te = new TestServiceException();
            te.setErrorCode(100);
            throw te;
        }
    }

    public Contestregistion isRegistedContest(Contestregistion contestregistion) throws TestServiceException {
        try {
            Contestregistion contest = contestTestDAO.isRegistedContest(contestregistion);
            return contest;
        } catch (PersistenceException e) {
            TestServiceException te = new TestServiceException();
            te.setErrorCode(100);
            throw te;
        }
    }


    public List<Contestregistion> getContestRegistionList(Testinfo testInfo) throws TestServiceException {
        try {
            TestServiceException te = new TestServiceException();
            List<Contestregistion> contestRegistionList = contestTestDAO.getContestRegistionList(testInfo);
            if (contestRegistionList == null) {
                te.setErrorCode(108);
                throw te;
            }
            return contestRegistionList;
        } catch (PersistenceException e) {
            TestServiceException te = new TestServiceException();
            te.setErrorCode(100);
            throw te;
        }
    }

    public Contestregistion changeContestStatus(Contestregistion contestregistion) throws TestServiceException {
        try {
            Testinfo testinfo = testDAO.getTestInfo(contestregistion.getTestId());
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            Timestamp startTime = testinfo.getStartTime();
            Timestamp endTime = testinfo.getEndTime();
            if (currentTime.before(endTime) && currentTime.after(startTime)) {
                Contestregistion contest = new Contestregistion();
                contestTestDAO.changeContestStatusBegin(contestregistion);
                contest = contestTestDAO.getContestRegistion(contestregistion);
                return contest;
            }
            else if (currentTime.after(endTime)) {
                Contestregistion contest = new Contestregistion();
                contestTestDAO.changeContestStatusEnd(contestregistion);
                contest = contestTestDAO.getContestRegistion(contestregistion);
                return contest;
            }else{
                return contestTestDAO.getContestRegistion(contestregistion);
            }
        } catch (PersistenceException e) {
            TestServiceException te = new TestServiceException();
            te.setErrorCode(100);
            throw te;
        }
    }

    public List<Testinfo> getContestInfoList() throws TestServiceException {
        try {
            TestServiceException te = new TestServiceException();
            List<Testinfo> contestInfoList = contestTestDAO.getContestInfoList();
            if (contestInfoList == null) {
                te.setErrorCode(109);
                throw te;
            }
            return contestInfoList;
        } catch (PersistenceException e) {
            TestServiceException te = new TestServiceException();
            te.setErrorCode(100);
            throw te;
        }
    }

    public List<Testinfo> getTestInfoList() throws TestServiceException {
        try {
            TestServiceException te = new TestServiceException();
            List<Testinfo> testInfoList = testDAO.getTestInfoList();
            if (testInfoList == null) {
                te.setErrorCode(110);
                throw te;
            }
            return testInfoList;
        } catch (PersistenceException e) {
            TestServiceException te = new TestServiceException();
            te.setErrorCode(100);
            throw te;
        }
    }

    public void insertTestInfo(Testinfo testinfo) throws TestServiceException {
        try {
            testDAO.insertTestInfo(testinfo);
        } catch (PersistenceException e) {
            TestServiceException te = new TestServiceException();
            te.setErrorCode(100);
            throw te;
        }
    }

    public void deleteTestInfo(int testId) throws TestServiceException {
        try {
            testDAO.deleteTestInfo(testId);
        } catch (PersistenceException e) {
            TestServiceException te = new TestServiceException();
            te.setErrorCode(100);
            throw te;
        }
    }

    public void updateTestInfo(Testinfo testinfo) throws TestServiceException {
        try {
            testDAO.updateTestInfo(testinfo);
        } catch (PersistenceException e) {
            TestServiceException te = new TestServiceException();
            te.setErrorCode(100);
            throw te;
        }
    }

    public Testinfo getTestInfo(int testId) throws TestServiceException {
        try {
            TestServiceException te = new TestServiceException();
            Testinfo testinfo = testDAO.getTestInfo(testId);
            if (testinfo == null) {
                te.setErrorCode(111);
                throw te;
            }
            return testinfo;
        } catch (PersistenceException e) {
            TestServiceException te = new TestServiceException();
            te.setErrorCode(100);
            throw te;
        }
    }

    public void insertTestRecord(Testrecord testrecord) throws TestServiceException {
        try {
            testRecordDAO.insertTestRecord(testrecord);
        } catch (PersistenceException e) {
            TestServiceException te = new TestServiceException();
            te.setErrorCode(100);
            throw te;
        }
    }

    public void updateTestRecord(Testrecord testrecord) throws TestServiceException {
        try {
            testRecordDAO.updateTestRecord(testrecord);
        } catch (PersistenceException e) {
            TestServiceException te = new TestServiceException();
            te.setErrorCode(100);
            throw te;
        }
    }

    public Testrecord getTestRecord(Testrecord testrecord) throws TestServiceException {
        try {
            TestServiceException te = new TestServiceException();
            Testrecord testrecord1 = testRecordDAO.getTestRecord(testrecord);
            return testrecord1;
        } catch (PersistenceException e) {
            TestServiceException te = new TestServiceException();
            te.setErrorCode(100);
            throw te;
        }
    }

    public List<Testrecord> getTestRecordList(int testId) throws TestServiceException {
        try {
            TestServiceException te = new TestServiceException();
            List<Testrecord> testRecordList = testRecordDAO.getTestRecordList(testId);
            if (testRecordList == null) {
                te.setErrorCode(113);
                throw te;
            }
            return testRecordList;
        } catch (PersistenceException e) {
            TestServiceException te = new TestServiceException();
            te.setErrorCode(100);
            throw te;
        }
    }

    public int getTestPersonTotalNum(int testId) throws TestServiceException {
        try {
            int count = testRecordDAO.getTestRecordList(testId).size();
            return count;
        } catch (PersistenceException e) {
            TestServiceException te = new TestServiceException();
            te.setErrorCode(100);
            throw te;
        }
    }

    public int getContestRank(Testrecord testrecord) throws TestServiceException {
        try {
            int count = 1;
            List<Testrecord> testrecordList = testRecordDAO.getTestRecordListByRecord(testrecord.getTestId());
            Testrecord testrecord1=testRecordDAO.getTestRecord(testrecord);
            for (int i = 0; i < testrecordList.size(); i++) {
                if (testrecordList.get(i).getScore() > testrecord1.getScore()) {
                    count++;
                }
            }
            return count;
        } catch (PersistenceException e) {
            TestServiceException te = new TestServiceException();
            te.setErrorCode(100);
            throw te;
        }
    }

    public void deleteTestRecord(Testrecord testrecord) throws TestServiceException {
        try {
            Testrecord testrecord1=testRecordDAO.getTestRecord(testrecord);
            testRecordDAO.deleteTestRecord(testrecord1);
        } catch (PersistenceException e) {
            TestServiceException te = new TestServiceException();
            te.setErrorCode(100);
            throw te;
        }
    }

    public void insertTesttitle(Testtitle testtitle) throws TestServiceException {
        try {
            testtitleDAO.insertTesttitle(testtitle);
        } catch (PersistenceException pe) {
            TestServiceException te = new TestServiceException();
            te.setErrorCode(100);
            throw te;
        }
    }

    public void insertTesttitle(Testrecord testrecord, String formatString) throws TestServiceException {
        Testtitle testtitle = new Testtitle();
        testtitle.setStudentId(testrecord.getStudentId());
        testtitle.setTestId(testrecord.getTestId());
        testtitle.setTitleIds(formatString);
        insertTesttitle(testtitle);
    }

    public Testtitle getTesttitleByTestrecord(Testrecord testrecord) throws TestServiceException {
        try {
            return testtitleDAO.getTesttitle(testrecord.getStudentId(), testrecord.getTestId());
        }catch (PersistenceException pe) {
            TestServiceException te = new TestServiceException();
            te.setErrorCode(100);
            throw te;
        }
    }

    public void deleteTestTitle(Testrecord testrecord) throws TestServiceException {
        try {
            testtitleDAO.deleteTesttitle(testrecord.getStudentId(), testrecord.getTestId());
        }catch (PersistenceException pe) {
            TestServiceException te = new TestServiceException();
            te.setErrorCode(100);
            throw te;
        }
    }

    public void insertContesttitle(Contesttitle contesttitle) throws TestServiceException {
        try {
            contestTitleDAO.insertContesttitle(contesttitle);
        }catch (PersistenceException pe) {
            TestServiceException te = new TestServiceException();
            te.setErrorCode(100);
            throw te;
        }
    }

    public Contesttitle getContesttitle(int testId) throws TestServiceException {
        try {
            Contesttitle contesttitle=contestTitleDAO.getContesttitle(testId);
            return contesttitle;
        }catch (PersistenceException pe) {
            TestServiceException te = new TestServiceException();
            te.setErrorCode(100);
            throw te;
        }
    }

    public Contesttitle getContesttitle(Testinfo testinfo) throws TestServiceException {
        try {
            Contesttitle contesttitle=contestTitleDAO.getContesttitle(testinfo);
            return contesttitle;
        }catch (PersistenceException pe) {
            TestServiceException te = new TestServiceException();
            te.setErrorCode(100);
            throw te;
        }
    }

    public Contesttitle getContesttitle(Contesttitle contesttitle) throws TestServiceException {
        try {
            Contesttitle contesttitle1=contestTitleDAO.getContesttitle(contesttitle);
            return contesttitle1;
        }catch (PersistenceException pe) {
            TestServiceException te = new TestServiceException();
            te.setErrorCode(100);
            throw te;
        }
    }

    public void deleteContesttitle(int testId) throws TestServiceException {
        try {
            contestTitleDAO.deleteContesttitle(testId);
        }catch (PersistenceException pe) {
            TestServiceException te = new TestServiceException();
            te.setErrorCode(100);
            throw te;
        }
    }

    public void deleteContesttitle(Testinfo testinfo) throws TestServiceException {
        try {
            contestTitleDAO.deleteContesttitle(testinfo);
        }catch (PersistenceException pe) {
            TestServiceException te = new TestServiceException();
            te.setErrorCode(100);
            throw te;
        }
    }

    public void deleteContesttitle(Contesttitle contesttitle) throws TestServiceException {
        try {
            contestTitleDAO.deleteContesttitle(contesttitle);
        }catch (PersistenceException pe) {
            TestServiceException te = new TestServiceException();
            te.setErrorCode(100);
            throw te;
        }
    }

    public List<AccountTestRecord> getTestRecordByCondition(String clazz, int grade, String major, String college, int level) throws TestServiceException{
        try {
            return testRecordDAO.getTestrecordByCondition(clazz, grade, major, college, level);
        }catch (PersistenceException pe) {
            TestServiceException te = new TestServiceException();
            te.setErrorCode(100);
            throw te;
        }
    }
}
