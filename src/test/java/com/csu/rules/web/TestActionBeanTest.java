package com.csu.rules.web;

import com.csu.rules.domain.*;
import com.csu.rules.persistence.*;
import com.csu.rules.persistence.impl.*;
import com.csu.rules.service.TestService;
import com.csu.rules.service.TitleService;
import com.csu.rules.service.impl.TestServiceimpl;
import com.csu.rules.service.impl.TitleServiceimpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import sun.font.TextRecord;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by GF on 2017/6/12.
 */
public class TestActionBeanTest {
    private TestDAO testDAO = new TestDAOimpl();
    private ContestTestDAO contestTestDAO = new ContestTestDAOimpl();
    private TestRecordDAO testRecordDAO = new TestRecordDAOimpl();
    private TitleDAO titleDAO = new TitleDAOimpl();
    private WrongtitleDAO wrongtitleDAO = new WrongtitleDAOimpl();
    TestService testService = new TestServiceimpl(contestTestDAO, testDAO, testRecordDAO);
    TitleService titleService = new TitleServiceimpl(titleDAO, wrongtitleDAO);
    TestActionBean testActionBean = new TestActionBean(testService, titleService);
    ObjectMapper mapper = new ObjectMapper();
    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();

    @Test
    public void testStartTest() throws Exception {
        Testrecord testrecord = new Testrecord();
        testrecord.setStudentId(3903150332L);
        testrecord.setTestId(1);
        testrecord.setStartTime(new Timestamp(System.currentTimeMillis()));
        String requestJson = ow.writeValueAsString(testrecord);
        MockMvc mockMvc = standaloneSetup(testActionBean).build();
        mockMvc.perform(post("/test/startTest")
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson.getBytes()))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void testRecordInfo() throws Exception {
        Testrecord testrecord = new Testrecord();
        testrecord.setStudentId(3903150326L);
        testrecord.setTestId(1);
        String requestJson = ow.writeValueAsString(testrecord);
        MockMvc mockMvc = standaloneSetup(testActionBean).build();
        mockMvc.perform(post("/test/testRecordInfo")
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson.getBytes()))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void testIsRegisted() throws Exception {
        Contestregistion contestregistion = new Contestregistion();
        contestregistion.setStudentId(3903150326L);
        contestregistion.setTestId(3);
        String requestJson = ow.writeValueAsString(contestregistion);
        MockMvc mockMvc = standaloneSetup(testActionBean).build();
        mockMvc.perform(post("/test/isRegisted")
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson.getBytes()))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void registContest() throws Exception {
        Contestregistion contestregistion = new Contestregistion();
        contestregistion.setStudentId(3903150327L);
        contestregistion.setTestId(3);
        String requestJson = ow.writeValueAsString(contestregistion);
        MockMvc mockMvc = standaloneSetup(testActionBean).build();
        mockMvc.perform(post("/test/registContest")
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson.getBytes()))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void testContestStatus() throws Exception {
        Contestregistion contestregistion = new Contestregistion();
        contestregistion.setStudentId(3903150327L);
        contestregistion.setTestId(3);
        String requestJson = ow.writeValueAsString(contestregistion);
        MockMvc mockMvc = standaloneSetup(testActionBean).build();
        mockMvc.perform(post("/test/getContestStatus")
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson.getBytes()))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }
    //    @Test
//    public void testSubmitTest() throws Exception {
//        List<Object> objectList=new ArrayList<Object>();
//
//        String requestJson = ow.writeValueAsString(objectList);
//        MockMvc mockMvc = standaloneSetup(testActionBean).build();
//        mockMvc.perform(post("/test/submitTest")
//                .characterEncoding("UTF-8")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(requestJson.getBytes()))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andReturn().getResponse().getContentAsString();
//    }
    @Test
    public void testInsertTestInfo() throws Exception {
        Testinfo testinfo=new Testinfo();
        testinfo.setType(new Integer(0).byteValue());
        testinfo.setName("中南大学第八次校规校纪测试");
        testinfo.setGrade(2017);
        testinfo.setStartTime(new Timestamp(System.currentTimeMillis()));
        testinfo.setEndTime(new Timestamp(System.currentTimeMillis()));
        testinfo.setDuration(100);
        String requestJson = ow.writeValueAsString(testinfo);
        MockMvc mockMvc = standaloneSetup(testActionBean).build();
        mockMvc.perform(post("/test/insertTest")
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson.getBytes()))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void testDeleteTestInfo() throws Exception {
        String requestJson = ow.writeValueAsString(5);
        MockMvc mockMvc = standaloneSetup(testActionBean).build();
        mockMvc.perform(post("/test/deleteTest")
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson.getBytes()))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void getAllRecord() throws Exception {
        String requestJson = ow.writeValueAsString(1);
        MockMvc mockMvc = standaloneSetup(testActionBean).build();
        mockMvc.perform(post("/test/getAllTestRecord")
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson.getBytes()))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }
    @Test
    public void testContestTitle() throws Exception {
        Set<Integer> set=new HashSet<Integer>();
        set.add(1);
        set.add(2);
        set.add(6);
        String requestJson = ow.writeValueAsString(set);
        MockMvc mockMvc = standaloneSetup(testActionBean).build();
        mockMvc.perform(post("/test/publishContestTitle")
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson.getBytes()))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }
    @Test
    public void testContestRank() throws Exception {
        Testrecord testrecord=new Testrecord();
        testrecord.setTestId(1);
        testrecord.setScore(99);
        String requestJson = ow.writeValueAsString(testrecord);
        MockMvc mockMvc = standaloneSetup(testActionBean).build();
        mockMvc.perform(post("/test/getContsetRank")
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson.getBytes()))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }
}
