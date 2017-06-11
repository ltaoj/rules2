package com.csu.rules.service.impl;

import com.csu.rules.domain.Title;
import com.csu.rules.persistence.ClockDAO;
import com.csu.rules.persistence.TitleDAO;
import com.csu.rules.persistence.WrongtitleDAO;
import com.csu.rules.persistence.impl.ClockDAOimpl;
import com.csu.rules.persistence.impl.TitleDAOimpl;
import com.csu.rules.persistence.impl.WrongtitleDAOimpl;
import com.csu.rules.service.LearnService;
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
    private LearnService learnService = new LearnServiceimpl(titleDAO, clockDAO, wrongtitleDAO);

    @Test
    public void testNotNULL() throws Exception {
        assertFalse(learnService == null);
        assertFalse(titleDAO == null);
        assertFalse(clockDAO == null);
        assertFalse(wrongtitleDAO == null);
    }

    @Test
    public void testGetTitleListByPage() throws Exception {
        assertTrue(learnService.getTitleListByPage(1, 10).size() == 2);
    }

    @Test
    public void testGetTitle() throws Exception {
        Title title = new Title();
        title.setTitleId(1);
        assertTrue(learnService.getTitle(title) != null);
    }
}
