package com.csu.rules.web;

import com.csu.rules.domain.*;
import com.csu.rules.exception.CatchServiceException;
import com.csu.rules.exception.TitleServiceException;
import com.csu.rules.service.TitleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ltaoj on 17-6-12.
 */
@Controller
@RequestMapping(value = "/title")
public class TitleActionBean extends AbstractActionBean{
    private TitleService titleService;
    @Autowired
    public TitleActionBean(TitleService titleService) {
        this.titleService = titleService;
    }

    @RequestMapping(value = "/practice", method = RequestMethod.GET)
    public ResponseEntity<Result> practice(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "count", defaultValue = "10") int count) {

        try {
            List<Title> titleList = titleService.getTitleListByPage(page, count);
            return new ResponseEntity<Result>(new Result(Result.RESULT_SUCCESS, titleList), HttpStatus.OK);
        } catch (TitleServiceException te) {
            throw new CatchServiceException(te);
        }
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Result> submit(@RequestBody AccountTitles accountTitles) {
        try {
            List<Title> checkedTitleList = titleService.submitTitleList(accountTitles.getAccount(), accountTitles.getTitleList());
            return new ResponseEntity<Result>(new Result(Result.RESULT_SUCCESS, checkedTitleList), HttpStatus.OK);
        } catch (TitleServiceException te) {
            throw new CatchServiceException(te);
        }
    }

    @RequestMapping(value = "/wrongList", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Result> wrongList(@RequestBody Account account) {
        try {
            List<Wrongtitle> wrongList = titleService.getWrongTitleListByStudentId(account);
            Set<Integer> idList = idListFromWrongtitles(wrongList);
            List<Title> titleList = titleService.getTitleListByTitleIds(idList);
            return new ResponseEntity<Result>(new Result(Result.RESULT_SUCCESS, titleList), HttpStatus.OK);
        } catch (TitleServiceException te) {
            throw new CatchServiceException(te);
        }
    }
    @RequestMapping(value = "/item/{titleId}", method = RequestMethod.GET)
    public ResponseEntity<Result> item(@PathVariable int titleId) {
        try {
            Title title = new Title();
            title.setTitleId(titleId);
            title = titleService.getTitle(title);
            return new ResponseEntity<Result>(new Result(Result.RESULT_SUCCESS, title), HttpStatus.OK);
        } catch (TitleServiceException te) {
            throw new CatchServiceException(te);
        }
    }

    @RequestMapping(value = "/item", method = RequestMethod.POST)
    public ResponseEntity<Result> item(@RequestBody Title title) {
        try {
            Title title1 = titleService.getTitle(title);
            return new ResponseEntity<Result>(new Result(Result.RESULT_SUCCESS, title1), HttpStatus.OK);
        } catch (TitleServiceException te) {
            throw new CatchServiceException(te);
        }
    }

    @RequestMapping(value = "addTitle", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Result> addTitle(@RequestBody Title title) {
        try {
            titleService.insertTitle(title);
            return new ResponseEntity<Result>(new Result(Result.RESULT_SUCCESS), HttpStatus.OK);
        } catch (TitleServiceException te) {
            throw new CatchServiceException(te);
        }
    }

    @RequestMapping(value = "addTitles", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Result> addTitles(@RequestBody List<Title> titleList) {
        try {
            titleService.insertTitleList(titleList);
            return new ResponseEntity<Result>(new Result(Result.RESULT_SUCCESS), HttpStatus.OK);
        }catch (TitleServiceException te) {
            throw new CatchServiceException(te);
        }
    }

    private Set<Integer> idListFromWrongtitles(List<Wrongtitle> wrongList) {
        Set<Integer> idList = new HashSet<Integer>();
        for (int i = 0;i < wrongList.size();i++) {
            idList.add(wrongList.get(i).getTitleId());
        }
        return idList;
    }

}
