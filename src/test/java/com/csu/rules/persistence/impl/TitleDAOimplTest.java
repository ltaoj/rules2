package com.csu.rules.persistence.impl;

import com.csu.rules.domain.Option;
import com.csu.rules.domain.Title;
import com.csu.rules.persistence.TitleDAO;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by ltaoj on 17-6-9.
 */
public class TitleDAOimplTest {

    private TitleDAO titleDAO = new TitleDAOimpl();

    @Test
    public void testAddTitle2() throws Exception {
        Title title = new Title();
        title.setName("4+4等于多少？");
        title.setScore(5);
        title.setDiffId(1);
        Set<Option> options = new HashSet<Option>();
        for (int i = 0;i < 4;i++) {
            Option option = new Option();
            option.setContent(String.valueOf(i + i));
            option.setChecked((byte)0);
            options.add(option);
        }
        title.setOptions(options);
        assertTrue(titleDAO.addTitle(title)!=null);
    }

    @Test
    public void testDeleteTitle() throws Exception {
        assertTrue(titleDAO.deleteTitle(12) == null);
    }

    @Test
    public void testGetTitle() throws Exception {
        assertTrue(titleDAO.getTitle(3).getOptions() != null);
        assertFalse(titleDAO.getTitle(100) != null);
    }

    @Test
    public void testGetTitleList() throws Exception {
        assertTrue(titleDAO.getTitleList(0, 10).size() <= 10);
    }

    @Test
    public void testGetRandomTitleList() throws Exception {
        assertTrue(titleDAO.getRandomTitleList(10) != null);
    }

    @Test
    public void testGetTotalTitleSize() throws Exception {
        assertTrue(titleDAO.getTotalTitleSize() == 10);
    }
}
