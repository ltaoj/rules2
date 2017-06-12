package com.csu.rules.web;

import com.csu.rules.domain.Account;
import com.csu.rules.domain.Signon;
import com.csu.rules.persistence.AccountDAO;
import com.csu.rules.persistence.AdminDAO;
import com.csu.rules.persistence.SignonDAO;
import com.csu.rules.persistence.impl.AccountDAOimpl;
import com.csu.rules.persistence.impl.AdminDAOimpl;
import com.csu.rules.persistence.impl.SignonDAOimpl;
import com.csu.rules.service.AccountService;
import com.csu.rules.service.impl.AccountServiceimpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by ltaoj on 17-6-12.
 */
public class AccountActionBeanTest {
    AccountDAO accountDAO = new AccountDAOimpl();
    SignonDAO signonDAO = new SignonDAOimpl();
    AdminDAO adminDAO = new AdminDAOimpl();
    AccountService accountService = new AccountServiceimpl(accountDAO, signonDAO, adminDAO);
    AccountActionBean accountActionBean = new AccountActionBean(accountService);

    ObjectMapper mapper = new ObjectMapper();
    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
    @Test
    public void testShowLoginForm() throws Exception {
        AccountService accountService = mock(AccountServiceimpl.class);
        AccountActionBean accountActionBean = new AccountActionBean(accountService);
        // 搭建MockMVC
        MockMvc mockMvc = standaloneSetup(accountActionBean)
                .setSingleView(new InternalResourceView("/WEB/jsp/loginForm.jsp"))
                .build();
        // 执行get请求，预期得到loginForm视图
        mockMvc.perform(get("/account/login"))
                .andExpect(view().name("loginForm"));
    }
    @Test
    public void testLogin() throws Exception {
        Signon signon = new Signon();
        signon.setStudentId(3903150326l);
        signon.setPassword("3903150326");
        String requestJson = ow.writeValueAsString(signon);
        MockMvc mockMvc = standaloneSetup(accountActionBean).build();
        mockMvc.perform(post("/account/login")
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson.getBytes()))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }
}
