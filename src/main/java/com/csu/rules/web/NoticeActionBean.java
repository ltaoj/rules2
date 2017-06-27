package com.csu.rules.web;

import com.csu.rules.domain.Notice;
import com.csu.rules.domain.Result;
import com.csu.rules.exception.CatchServiceException;
import com.csu.rules.exception.NoticeServiceException;
import com.csu.rules.service.NoticeService;
import javafx.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by CMM on 2017/6/12.
 */
@Controller
@RequestMapping(value = {"/notice"})
public class NoticeActionBean extends AbstractActionBean {
    private NoticeService noticeService;

    @Autowired
    public NoticeActionBean(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @RequestMapping(value = "/getPictureNotice", method = RequestMethod.GET)
    public ResponseEntity<List<Notice>> getPictureNotice() {
        try {
            List<Notice> noticeList = noticeService.getPictrueNoticeList();
            return new ResponseEntity<List<Notice>>(noticeList, HttpStatus.OK);
        } catch (NoticeServiceException e) {
            throw new CatchServiceException(e);
        }
    }

    @RequestMapping(value = "/getAllTextNotice", method = RequestMethod.GET)
    public ResponseEntity<List<Notice>> getTextPictureNotice() {
        try {
            List<Notice> noticeList = noticeService.getTextNoticeList();
            return new ResponseEntity<List<Notice>>(noticeList, HttpStatus.OK);
        } catch (NoticeServiceException e) {
            throw new CatchServiceException(e);
        }
    }

    @RequestMapping(value = "/getTextNotice", method = RequestMethod.GET)
    public ResponseEntity<List<Notice>> getTextPictureNotice(@RequestParam int offset, @RequestParam int count) {
        try {
            List<Notice> noticeList = noticeService.getTextListByPage(offset, count);
            return new ResponseEntity<List<Notice>>(noticeList, HttpStatus.OK);
        } catch (NoticeServiceException e) {
            throw new CatchServiceException(e);
        }
    }

    @RequestMapping(value = "/getNotice", method = RequestMethod.GET)
    public ResponseEntity<Notice> getNotice(@RequestParam int noticeId) {
        try {
            Notice notice = noticeService.getNotice(noticeId);
            return new ResponseEntity<Notice>(notice, HttpStatus.OK);
        } catch (NoticeServiceException e) {
            throw new CatchServiceException(e);
        }
    }

    @RequestMapping(value = "/publishNotice", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Result> publishNotice(@RequestBody Notice notice) {
        try {
            noticeService.publishNotice(notice);
            return new ResponseEntity<Result>(new Result(Result.RESULT_SUCCESS), HttpStatus.OK);
        } catch (NoticeServiceException e) {
            throw new CatchServiceException(e);
        }
    }

}
