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
    private ClockDAO clockDao;

    @Autowired
    public LearnServiceimpl(ClockDAO clockDao) {
        this.clockDao = clockDao;
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
}
