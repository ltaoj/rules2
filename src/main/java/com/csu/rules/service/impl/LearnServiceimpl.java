package com.csu.rules.service.impl;

import com.csu.rules.domain.*;
import com.csu.rules.exception.LearnServiceException;
import com.csu.rules.exception.PersistenceException;
import com.csu.rules.persistence.ClockDAO;
import com.csu.rules.persistence.TitleDAO;
import com.csu.rules.persistence.WrongtitleDAO;
import com.csu.rules.persistence.impl.ClockDAOimpl;
import com.csu.rules.persistence.impl.TitleDAOimpl;
import com.csu.rules.persistence.impl.WrongtitleDAOimpl;
import com.csu.rules.service.LearnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by ltaoj on 17-6-10.
 */
@Service
public class LearnServiceimpl implements LearnService {
    private TitleDAO titleDAO;
    private ClockDAO clockDao;
    private WrongtitleDAO wrongtitleDAO;

    @Autowired
    public LearnServiceimpl(TitleDAO titleDAO, ClockDAO clockDao, WrongtitleDAO wrongtitleDAO) {
        this.titleDAO = titleDAO;
        this.clockDao = clockDao;
        this.wrongtitleDAO = wrongtitleDAO;
    }

    public List<Title> getTitleListByPage(int page, int count) throws LearnServiceException {
        try {
            LearnServiceException le = new LearnServiceException();
            if(page < 0 ) {
                le.setErrorCode(51);
                throw le;
            }else if (count < 0) {
                le.setErrorCode(52);
                throw le;
            }
            List<Title> titleList = titleDAO.getTitleList((page - 1) * count, count);
            return titleList;
        } catch (PersistenceException pe) {
            LearnServiceException le = new LearnServiceException(pe);
            le.setErrorCode(50);
            throw le;
        }
    }

    public List<Title> getTitleListByRandom(int count) throws LearnServiceException {
        try {
            LearnServiceException le = new LearnServiceException();
            if (count < 0) {
                le.setErrorCode(52);
                throw le;
            }
            List<Title> titleList = titleDAO.getRandomTitleList(count);
            return titleList;
        }catch (PersistenceException pe) {
            LearnServiceException le = new LearnServiceException(pe);
            le.setErrorCode(50);
            throw le;
        }
    }

    public List<Title> submitTitleList(Account account, List<Title> titleList) throws LearnServiceException {
        try {
            LearnServiceException le = new LearnServiceException();
            if (titleList == null || titleList.size() == 0) {
                le.setErrorCode(53);
                throw le;
            }
            List<Title> list = new ArrayList<Title>(titleList.size());
            for (int i = 0;i < list.size();i++) {
                Title title = getCorrectTitle(titleList.get(i));
                if (title.getOptions() != null) {
                    Wrongtitle wrongtitle = new Wrongtitle();
                    wrongtitle.setStudentId(account.getStudentId());
                    wrongtitle.setTitleId(titleList.get(i).getTitleId());
                    wrongtitleDAO.insertWrongTitle(wrongtitle);
                }
                list.add(title);
            }
            return list;
        }catch (PersistenceException pe) {
            LearnServiceException le = new LearnServiceException(pe);
            le.setErrorCode(50);
            throw le;
        }
    }

    public Title getCorrectTitle(Title title) throws LearnServiceException {
        try {
            LearnServiceException le = new LearnServiceException();
            if (title == null || title.getOptions() == null) {
                le.setErrorCode(54);
                throw le;
            }
            Title title1 = titleDAO.getTitle(title.getTitleId());
            if (title1 == null) {
                le.setErrorCode(55);
                throw le;
            }
            // 检查答案是否与题库一致
            boolean flag = true;
            while (title.getOptions().iterator().hasNext() && title1.getOptions().iterator().hasNext()) {
                if (title.getOptions().iterator().next() != title1.getOptions().iterator().next()) {
                    flag = false;
                    title.setOptions(null);
                    break;
                }
            }
            return flag ? title : title1;
        } catch (PersistenceException pe) {
            LearnServiceException le = new LearnServiceException(pe);
            le.setErrorCode(50);
            throw le;
        }
    }

    public boolean isTitleCorrect(Title title) throws LearnServiceException {
        return getCorrectTitle(title).getOptions() == null ? true : false;
    }

    public List<Wrongtitle> getWrongTitleListByStudentId(Account account) throws LearnServiceException {
        try {
            LearnServiceException le = new LearnServiceException();
            if (Long.toString(account.getStudentId()).length() != 10 || account.getUsername() == null) {
                le.setErrorCode(56);
                throw le;
            }
            List<Wrongtitle> wrongtitleList = wrongtitleDAO.getWrongTitleIdList(account);
            return wrongtitleList;
        } catch (PersistenceException pe) {
            LearnServiceException le = new LearnServiceException(pe);
            le.setErrorCode(50);
            throw le;
        }
    }

    public List<Title> getTitleListByTitleIds(Set<Integer> integers) throws LearnServiceException {
        try {
            LearnServiceException le = new LearnServiceException();
            if (integers == null || integers.size() == 0) {
                le.setErrorCode(57);
                throw le;
            }
            List<Title> list = titleDAO.getTitleListByTitleIds(integers);
            return list;
        } catch (PersistenceException pe) {
            LearnServiceException le = new LearnServiceException(pe);
            le.setErrorCode(50);
            throw le;
        }
    }

    public Title getTitle(Title title) throws LearnServiceException {
        try {
            LearnServiceException le = new LearnServiceException();
            if (title == null) {
                le.setErrorCode(58);
                throw le;
            }
            Title title1 = titleDAO.getTitle(title.getTitleId());
            if (title1 == null) {
                le.setErrorCode(59);
                throw le;
            }
            return title1;
        } catch (PersistenceException pe) {
            LearnServiceException le = new LearnServiceException(pe);
            le.setErrorCode(50);
            throw le;
        }
    }

    public int getTitlePageScore(List<Title> titleList) throws LearnServiceException {
        try {
            LearnServiceException le = new LearnServiceException();
            if (titleList == null || titleList.size() == 0) {
                le.setErrorCode(60);
                throw le;
            }
            int score = 0;
            for (int i = 0;i < titleList.size();i++) {
                if (isTitleCorrect(titleList.get(i))) {
                    score += titleList.get(i).getScore();
                }
            }
            return score;
        }catch (PersistenceException pe) {
            LearnServiceException le = new LearnServiceException(pe);
            le.setErrorCode(50);
            throw le;
        }
    }

    public void insertClock(Clockin clockin) throws LearnServiceException {
        try {
            LearnServiceException le = new LearnServiceException();
            if (clockin == null) {
                le.setErrorCode(61);
                throw le;
            }
            clockDao.insertClock(clockin);
        }catch (PersistenceException pe) {
            LearnServiceException le = new LearnServiceException(pe);
            le.setErrorCode(50);
            throw le;
        }
    }

    public List<Clockin> getAllClocks(Account account) throws LearnServiceException {
        try {
            LearnServiceException le = new LearnServiceException();
            if (Long.toString(account.getStudentId()).length() != 10 || account.getUsername() == null) {
                le.setErrorCode(56);
                throw le;
            }
            List<Clockin> clockinList = clockDao.getAllClocks(account.getStudentId());
            return clockinList;
        } catch (PersistenceException pe) {
            LearnServiceException le = new LearnServiceException(pe);
            le.setErrorCode(50);
            throw le;
        }
    }

    public Clockin getClockDetailByDay(Account account, Timestamp datetime) throws LearnServiceException {
        try {
            LearnServiceException le = new LearnServiceException();
            if (Long.toString(account.getStudentId()).length() != 10 || account.getUsername() == null) {
                le.setErrorCode(56);
                throw le;
            }else if (datetime == null) {
                le.setErrorCode(62);
                throw le;
            }
            Clockin clockin = clockDao.getClockByDay(account.getStudentId(), datetime);
            return clockin;
        } catch (PersistenceException pe) {
            LearnServiceException le = new LearnServiceException(pe);
            le.setErrorCode(50);
            throw le;
        }
    }

    public void updateTodayClock(Clockin clockin) throws LearnServiceException {
        try {
            LearnServiceException le = new LearnServiceException();
            if (clockin == null) {
                le.setErrorCode(61);
                throw le;
            }
            clockDao.updateClock(clockin);
        } catch (PersistenceException pe) {
            LearnServiceException le = new LearnServiceException(pe);
            le.setErrorCode(50);
            throw le;
        }
    }

    public static void main(String[] args) {
        TitleDAO titleDAO = new TitleDAOimpl();
        ClockDAO clockDao = new ClockDAOimpl();
        WrongtitleDAO wrongtitleDAO = new WrongtitleDAOimpl();
        LearnServiceimpl learnServiceimpl = new LearnServiceimpl(titleDAO, clockDao, wrongtitleDAO);
        try {
            System.out.println(learnServiceimpl.getTitleListByPage(1, 10));
        } catch (LearnServiceException e) {
            e.printStackTrace();
        }
        Title title = new Title();
        title.setTitleId(1);
        try {
            Title title1 =learnServiceimpl.getTitle(title);
            System.out.println(title1.getName());
        } catch (LearnServiceException e) {
            e.printStackTrace();
        }
    }
}
