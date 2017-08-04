package com.csu.rules.web;

import com.csu.rules.domain.*;
import com.csu.rules.exception.AccountServiceException;
import com.csu.rules.exception.CatchServiceException;
import com.csu.rules.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by ltaoj on 17-6-4.
 */
@Controller
@RequestMapping(value = {"/account"})
public class AccountActionBean extends AbstractActionBean {

    private AccountService accountService;

    @Autowired
    public AccountActionBean(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginForm() {
        return "loginForm";
    }

    @RequestMapping(value = "/adminLoginForm", method = RequestMethod.GET)
    public String showadminLoginForm() {
        return "adminLogin";
    }

    @RequestMapping(value = "/backstage", method = RequestMethod.POST)
    public String showBackstageForm(String account, String password,ModelMap modelMap) {
        try {
            Admin admin = new Admin();
            admin.setAccount(account);
            admin.setPassword(password);
            Admin admin1 = accountService.login(admin);
            if(admin1==null){
                modelMap.addAttribute("msg","账号或密码错误");
                return "adminLogin";
            }else{
                modelMap.addAttribute("admin",admin1);
                return "Backstage";
            }
        }catch (AccountServiceException e){
            throw new CatchServiceException(e);
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Account> login(@RequestBody Signon signon) {
        try {
            Account account1 = accountService.login(signon);
            return new ResponseEntity<Account>(account1, HttpStatus.OK);
        } catch (AccountServiceException e) {
            throw new CatchServiceException(e);
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Account> logintest(
            @RequestParam(value = "studentId") long studentId,
            @RequestParam(value = "password") String password) {
        try {
            Account account = accountService.login(studentId, password);
            return new ResponseEntity<Account>(account, HttpStatus.OK);
        } catch (AccountServiceException e) {
            throw new CatchServiceException(e);
        }
    }

    @RequestMapping(value = "/adminLogin", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Admin> adminLogin(@RequestBody Admin admin) {
        try {
            Admin admin1=accountService.login(admin);
            return new ResponseEntity<Admin>(admin1, HttpStatus.OK);
        } catch (AccountServiceException e) {
            throw new CatchServiceException(e);
        }
    }

    @RequestMapping(value = "/insertAdmin", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Result> insertAdmin(@RequestBody Admin admin) {
        try {
            Admin admin1=accountService.login(admin);
            if(admin1==null) {
                System.out.println(admin1);
                accountService.insertAdmin(admin);
                return new ResponseEntity<Result>(new Result(Result.RESULT_SUCCESS), HttpStatus.OK);
            }else{
                return new ResponseEntity<Result>(new Result(Result.RESULT_ERROR), HttpStatus.OK);
            }
        } catch (AccountServiceException e) {
            throw new CatchServiceException(e);
        }
    }

    @RequestMapping(value = "/insertFeedback", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Result> insertFeedback(@RequestBody Feedback feedback) {
        try {
            feedback.setSubmitTime(new Timestamp(System.currentTimeMillis()));
            accountService.insertFeedback(feedback);
            return new ResponseEntity<Result>(new Result(Result.RESULT_SUCCESS), HttpStatus.OK);
        } catch (AccountServiceException e) {
            throw new CatchServiceException(e);
        }
    }

    @RequestMapping(value = "/getFeedbackList", method = RequestMethod.GET)
    public ResponseEntity<List<Feedback>> getFeedbackList() {
        try {
            List<Feedback> list=accountService.getFeedbackList();
            return new ResponseEntity<List<Feedback>>(list, HttpStatus.OK);
        } catch (AccountServiceException e) {
            throw new CatchServiceException(e);
        }
    }
}
