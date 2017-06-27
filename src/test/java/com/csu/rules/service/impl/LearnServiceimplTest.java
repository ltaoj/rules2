package com.csu.rules.service.impl;

import com.csu.rules.domain.Title;
import com.csu.rules.persistence.ClockDAO;
import com.csu.rules.persistence.TitleDAO;
import com.csu.rules.persistence.WrongtitleDAO;
import com.csu.rules.persistence.impl.ClockDAOimpl;
import com.csu.rules.persistence.impl.TitleDAOimpl;
import com.csu.rules.persistence.impl.WrongtitleDAOimpl;
import com.csu.rules.service.LearnService;
import com.csu.rules.service.TitleService;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Created by ltaoj on 17-6-11.
 */
public class LearnServiceimplTest {

    private TitleDAO titleDAO = new TitleDAOimpl();
    private ClockDAO clockDAO = new ClockDAOimpl();
    private WrongtitleDAO wrongtitleDAO = new WrongtitleDAOimpl();
    private LearnService learnService = new LearnServiceimpl(clockDAO);
    private TitleService titleService = new TitleServiceimpl(titleDAO, wrongtitleDAO);

    @Test
    public void testNotNULL() throws Exception {
        assertFalse(learnService == null);
        assertFalse(titleDAO == null);
        assertFalse(clockDAO == null);
        assertFalse(wrongtitleDAO == null);
    }

    @Test
    public void testGetTitleListByPage() throws Exception {
        assertTrue(titleService.getTitleListByPage(1, 10).size() == 2);
    }

    @Test
    public void testGetTitle() throws Exception {
        Title title = new Title();
        title.setTitleId(1);
        assertTrue(titleService.getTitle(title) != null);
    }

    @Test
    public void testGetTitleListByRandom() throws Exception {
        assertTrue(titleService.getTitleListByRandom(2).size() == 2);
    }
}
