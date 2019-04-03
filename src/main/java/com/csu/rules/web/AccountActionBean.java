package com.csu.rules.web;

import com.csu.rules.domain.*;
import com.csu.rules.exception.AccountServiceException;
import com.csu.rules.exception.CatchServiceException;
import com.csu.rules.service.AccountService;
import com.csu.rules.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ltaoj on 17-6-4.
 */
@Controller
@RequestMapping(value = {"/account"})
public class AccountActionBean extends AbstractActionBean {

    private AccountService accountService;
    private MailService mailService;

    @Autowired
    public AccountActionBean(AccountService accountService, MailService mailService) {
        this.accountService = accountService;
        this.mailService = mailService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginForm() throws IOException {
        return "loginForm";
    }

    @RequestMapping(value = "/adminLoginForm", method = RequestMethod.GET)
    public String showadminLoginForm() {
        return "adminLogin";
    }

    @RequestMapping(value = "/backstage", method = RequestMethod.POST)
    public String showBackstageForm(String account, String password, ModelMap modelMap) {
        try {
            Admin admin = new Admin();
            admin.setAccount(account);
            admin.setPassword(password);
            Admin admin1 = accountService.login(admin);
            if (admin1 == null) {
                modelMap.addAttribute("msg", "账号或密码错误");
                return "adminLogin";
            } else {
                modelMap.addAttribute("admin", admin1);
                return "Backstage";
            }
        } catch (AccountServiceException e) {
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
            @RequestParam(value = "studentId") String studentId,
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
            Admin admin1 = accountService.login(admin);
            return new ResponseEntity<Admin>(admin1, HttpStatus.OK);
        } catch (AccountServiceException e) {
            throw new CatchServiceException(e);
        }
    }

    @RequestMapping(value = "/insertAdmin", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Result> insertAdmin(@RequestBody Admin admin) {
        try {
            Admin admin1 = accountService.login(admin);
            if (admin1 == null) {
                System.out.println(admin1);
                accountService.insertAdmin(admin);
                return new ResponseEntity<Result>(new Result(Result.RESULT_SUCCESS), HttpStatus.OK);
            } else {
                return new ResponseEntity<Result>(new Result(Result.RESULT_ERROR), HttpStatus.OK);
            }
        } catch (AccountServiceException e) {
            throw new CatchServiceException(e);
        }
    }

    @RequestMapping(value = "/registerForInfo", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Result> register(@RequestBody Account account) {
        try {
            accountService.insertUser(account);
            return new ResponseEntity<Result>(new Result(Result.RESULT_SUCCESS), HttpStatus.OK);
        } catch (AccountServiceException e) {
            throw new CatchServiceException(e);
        }
    }

    @RequestMapping(value = "/registerForAccount", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Result> register(@RequestBody Signon signon) {
        try {
            accountService.insertSignon(signon);
            return new ResponseEntity<Result>(new Result(Result.RESULT_SUCCESS), HttpStatus.OK);
        } catch (AccountServiceException e) {
            throw new CatchServiceException(e);
        }
    }

    @RequestMapping(value = "/insertFeedback", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Result> insertFeedback(@RequestBody Feedback feedback) {
        try {
            feedback.setSubmitTime(new Timestamp(System.currentTimeMillis()));
            accountService.insertFeedback(feedback);

            final Feedback feedback1 = feedback;
            // ltaoj 2018年05月05日18:27:53 添加自动回复和通知功能
            new Thread(new Runnable() {
                public void run() {
                    mailService.autoReply(feedback1);
                    mailService.forwardFeedback(feedback1);
                }
            }).start();

            return new ResponseEntity<Result>(new Result(Result.RESULT_SUCCESS), HttpStatus.OK);
        } catch (AccountServiceException e) {
            throw new CatchServiceException(e);
        }
    }

    @RequestMapping(value = "/getFeedbackList", method = RequestMethod.GET)
    public ResponseEntity<List<Feedback>> getFeedbackList() {
        try {
            List<Feedback> list = accountService.getFeedbackList();
            return new ResponseEntity<List<Feedback>>(list, HttpStatus.OK);
        } catch (AccountServiceException e) {
            throw new CatchServiceException(e);
        }
    }

    // 2018/04/22添加 只用于批量添加AccountInfo
    @RequestMapping(value = "/insertAccount", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Result> register(@RequestBody List<Account> accountList) {
        try {
            accountService.insertUserList(accountList);
            return new ResponseEntity<Result>(new Result(Result.RESULT_SUCCESS), HttpStatus.OK);
        } catch (AccountServiceException e) {
            throw new CatchServiceException(e);
        }
    }

    //    sun 20190401 有点忘了之前的代码结构了...
    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getUser(HttpServletRequest request, HttpServletResponse response) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            String accountId = request.getParameter("accountId");
            Account query = new Account();
            query.setStudentId(accountId);
            Account account = accountService.getUserInfo(query);
            map.put("Msg", "success");
            map.put("username", account.getUsername());
            return map;
        } catch (AccountServiceException e) {
            throw new CatchServiceException(e);
        }
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> updateUser(HttpServletRequest request, HttpServletResponse response) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            String accountId = request.getParameter("accountId");
            Account query = new Account();
            query.setStudentId(accountId);
            Account account = accountService.getUserInfo(query);
            Signon signon = accountService.getSignon(accountId);
            String type = request.getParameter("type");
            if(account != null) {
                if ("reset".equals(type)) {
                    signon.setPassword("123456");
                    accountService.updateSignon(signon);
                } else if ("delete".equals(type)) {
                    account.setDelFlag(1);
                    accountService.updateUser(account);
                } else if ("update".equals(type)) {
                    String username = request.getParameter("username");
                    account.setUsername(username);
                    accountService.updateUser(account);
                }
            } else {
                map.put("Msg", "fail");
            }
            return map;
        } catch (AccountServiceException e) {
            throw new CatchServiceException(e);
        }
    }
}
