package com.csu.rules.persistence.impl;

import com.csu.rules.domain.Option;
import com.csu.rules.domain.Title;
import com.csu.rules.persistence.TitleDAO;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ltaoj on 17-6-9.
 */
public class TitleDAOimplTest {

    private TitleDAO titleDAO = new TitleDAOimpl();
    @Test
    public void testAddTitle() throws Exception {
        Title title = new Title();
        title.setName("3+3等于多少？");
        title.setScore(5);
        title.setDiffId(1);
        List<Option> optionList = new ArrayList<Option>();
        for (int i = 0;i < 4;i++) {
            Option option = new Option();
            option.setContent(String.valueOf(i + i));
            option.setChecked((byte)0);
            optionList.add(option);
        }
        optionList.get(3).setChecked((byte)1);

        titleDAO.addTitle(title, optionList);
    }
}
