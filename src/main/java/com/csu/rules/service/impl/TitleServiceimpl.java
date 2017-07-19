package com.csu.rules.service.impl;

import com.csu.rules.domain.*;
import com.csu.rules.exception.PersistenceException;
import com.csu.rules.exception.TitleServiceException;
import com.csu.rules.persistence.*;
import com.csu.rules.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by ltaoj on 17-6-11.
 */
@Service
public class TitleServiceimpl implements TitleService {

    private TitleDAO titleDAO;
    private WrongtitleDAO wrongtitleDAO;
    private AdditiontitleDAO additiontitleDAO;

    @Autowired
    public TitleServiceimpl(TitleDAO titleDAO, WrongtitleDAO wrongtitleDAO, AdditiontitleDAO additiontitleDAO) {
        this.titleDAO = titleDAO;
        this.wrongtitleDAO = wrongtitleDAO;
        this.additiontitleDAO = additiontitleDAO;
    }

    public List<Title> getTitleListByPage(int page, int count) throws TitleServiceException {
        try {
            TitleServiceException te = new TitleServiceException();
            if (page < 0) {
                te.setErrorCode(51);
                throw te;
            } else if (count < 0) {
                te.setErrorCode(52);
                throw te;
            }
            List<Title> titleList = titleDAO.getTitleList(page == 0 ? 0 : (page - 1) * count, count);
            // 将答案选项统一设置为0
            for (int i = 0; i < titleList.size(); i++) {
                Iterator<Option> iterator = titleList.get(i).getOptions().iterator();
                while (iterator.hasNext()) {
                    iterator.next().setChecked((byte) 0);
                }
            }
            return titleList;
        } catch (PersistenceException pe) {
            TitleServiceException te = new TitleServiceException(pe);
            te.setErrorCode(50);
            throw te;
        }
    }

    public List<Additiontitle> getTitleListByTypeAndPage(int page, int count, int type) throws TitleServiceException {
        try {
            TitleServiceException te = new TitleServiceException();
            if (page < 0) {
                te.setErrorCode(51);
                throw te;
            } else if (count < 0) {
                te.setErrorCode(52);
                throw te;
            } else if (type < 0) {
                te.setErrorCode(64);
                throw te;
            }
            List<Additiontitle> titleList = additiontitleDAO.getTitleListByTypeAndPage(type, page == 0 ? 0 : (page - 1) * count, count);
            // 将题目的答案设置为空字符
            for (int i = 0;i < titleList.size();i++) {
                titleList.get(i).setAnswer("");
            }
            return titleList;
        } catch (PersistenceException pe) {
            TitleServiceException te = new TitleServiceException(pe);
            te.setErrorCode(50);
            throw te;
        }
    }

    public List<Title> getTitleListByRandom(int count) throws TitleServiceException {
        try {
            TitleServiceException te = new TitleServiceException();
            if (count < 0) {
                te.setErrorCode(52);
                throw te;
            }
            List<Title> titleList = titleDAO.getRandomTitleList(count);
            // 将答案选项统一设置为0
            for (int i = 0; i < titleList.size(); i++) {
                Iterator<Option> iterator = titleList.get(i).getOptions().iterator();
                while (iterator.hasNext()) {
                    iterator.next().setChecked((byte) 0);
                }
            }
            return titleList;
        } catch (PersistenceException pe) {
            TitleServiceException te = new TitleServiceException(pe);
            te.setErrorCode(50);
            throw te;
        }
    }

    public List<Additiontitle> getRandomTitleListByType(int count, int type) throws TitleServiceException {
        try {
            TitleServiceException te = new TitleServiceException();
            if (count < 0) {
                te.setErrorCode(52);
                throw te;
            } else if (type < 0 || type > 4) { // 新加的有5类题目
                te.setErrorCode(64);
                throw te;
            }
            List<Additiontitle> titleList = additiontitleDAO.getRandomTitleListByType(count, type);
            // 将答案设置为空字符
            for (int i = 0;i < titleList.size();i++) {
                titleList.get(i).setAnswer("");
            }
            return titleList;
        } catch (PersistenceException pe) {
            TitleServiceException te = new TitleServiceException(pe);
            te.setErrorCode(50);
            throw te;
        }
    }

    public List<Title> submitTitleList(Account account, List<Title> titleList) throws TitleServiceException {
        try {
            TitleServiceException te = new TitleServiceException();
            if (titleList == null || titleList.size() == 0) {
                te.setErrorCode(53);
                throw te;
            }
            List<Title> list = new ArrayList<Title>(titleList.size());
            for (int i = 0; i < titleList.size(); i++) {
                Title title = getCorrectTitle(titleList.get(i));
                Wrongtitle wrongtitle = new Wrongtitle();
                wrongtitle.setStudentId(account.getStudentId());
                wrongtitle.setTitleId(titleList.get(i).getTitleId());
                boolean isWrongTitle = wrongtitleDAO.getWrongTitle(wrongtitle) != null;
                // 如果title的options为空说明为错题，接着判断是否已有记录，没有记录的话插入；
                // 如果是正确的，那么options自然不为空，那么看如果已有记录，则删除
                if (title.getOptions() == null) {
                    if (!isWrongTitle)
                        wrongtitleDAO.insertWrongTitle(wrongtitle);
                } else if (isWrongTitle) {
                    wrongtitleDAO.deleteWrongTitle(wrongtitle);
                }
                list.add(title);
            }
            return list;
        } catch (PersistenceException pe) {
            TitleServiceException te = new TitleServiceException(pe);
            te.setErrorCode(50);
            throw te;
        }
    }

    public List<Additiontitle> submitAdditionList(Account account, List<Additiontitle> titleList) throws TitleServiceException {
        try {
            TitleServiceException te = new TitleServiceException();
            if (titleList == null || titleList.size() == 0) {
                te.setErrorCode(53);
                throw te;
            }
            List<Additiontitle> list = new ArrayList<Additiontitle>(titleList.size());
            for (int i = 0;i < titleList.size();i++) {
                Additiontitle title = getCorrectTitle(titleList.get(i));
                list.add(title);
            }
            return list;
        } catch (PersistenceException pe) {
            TitleServiceException te = new TitleServiceException(pe);
            te.setErrorCode(50);
            throw te;
        }
    }

    // 如果题目本身正确，那么将直接返回题目，如果题目错误，选项设为null后返回
    public Title getCorrectTitle(Title title) throws TitleServiceException {
        try {
            TitleServiceException te = new TitleServiceException();
            if (title == null || title.getOptions() == null) {
                te.setErrorCode(54);
                throw te;
            }
            Title title1 = titleDAO.getTitle(title.getTitleId());
            if (title1 == null) {
                te.setErrorCode(55);
                throw te;
            }
            // 检查答案是否与题库一致
            Iterator<Option> iterator1 = title.getOptions().iterator();
            Iterator<Option> iterator2 = title1.getOptions().iterator();
            boolean flag = true;
            Option option1 = null;
            Option option2 = null;
            while (iterator1.hasNext()) {
                option1 = iterator1.next();
                if (option1.getChecked() == 1) break;
            }
            while (iterator2.hasNext()) {
                option2 = iterator2.next();
                if (option2.getChecked() == 1) break;
            }

            if (option1.getOptionId() != option2.getOptionId()) flag = false;
            // 如果题目错误，则把选项设置为null
            if (!flag) {
                title.setOptions(null);
            }
            return title;
        } catch (PersistenceException pe) {
            TitleServiceException te = new TitleServiceException(pe);
            te.setErrorCode(50);
            throw te;
        }
    }

    public Additiontitle getCorrectTitle(Additiontitle title) throws TitleServiceException {
        try {
            TitleServiceException te = new TitleServiceException();
            if (title == null) {
                te.setErrorCode(54);
                throw te;
            }
            Additiontitle title1 = additiontitleDAO.getTitle(title.getTitleId());
            if (title1 == null) {
                te.setErrorCode(55);
                throw te;
            }
            String[] standardAnswers = title1.getAnswer().split("#");
            String[] userAnswers = title.getAnswer().split("#");
            if (standardAnswers.length != userAnswers.length) {
                te.setErrorCode(65);
                throw te;
            }
            // 检查答案是否一致
            // 比如填空题正确答案是"爱国#守法#明礼#诚信"
            // 用户填的答案经前端传过来是"爱国#-#明礼#敬业"(没填的要有占位字符)
            // 经该方法判断后答案处理为1#0#1#0
            // 只有两个空正确
            StringBuffer sb = new StringBuffer();
            for (int i = 0;i < userAnswers.length;i++) {
                if (userAnswers[i].trim().equals(standardAnswers[i].trim()))
                    sb.append("#1");
                else
                    sb.append("#0");
            }
            String answer = sb.toString();
            // 设置改正后的答案
            title.setAnswer(answer.substring(1, answer.length()));
            return title;
        } catch (PersistenceException pe) {
            TitleServiceException te = new TitleServiceException(pe);
            te.setErrorCode(50);
            throw te;
        }
    }

    public boolean isTitleCorrect(Title title) throws TitleServiceException {
        return getCorrectTitle(title).getOptions() == null ? false : true;
    }

    public List<Wrongtitle> getWrongTitleListByStudentId(Account account) throws TitleServiceException {
        try {
            TitleServiceException te = new TitleServiceException();
            if (Long.toString(account.getStudentId()).length() != 10 || account.getUsername() == null) {
                te.setErrorCode(56);
                throw te;
            }
            List<Wrongtitle> wrongtitleList = wrongtitleDAO.getWrongTitleIdList(account);
            return wrongtitleList;
        } catch (PersistenceException pe) {
            TitleServiceException te = new TitleServiceException(pe);
            te.setErrorCode(50);
            throw te;
        }
    }

    public List<Title> getTitleListByTitleIds(Set<Integer> integers) throws TitleServiceException {
        try {
            TitleServiceException te = new TitleServiceException();
            if (integers == null || integers.size() == 0) {
                te.setErrorCode(57);
                throw te;
            }
            List<Title> list = titleDAO.getTitleListByTitleIds(integers);
            return list;
        } catch (PersistenceException pe) {
            TitleServiceException te = new TitleServiceException(pe);
            te.setErrorCode(50);
            throw te;
        }
    }

    public List<Additiontitle> getAdditiontitleListByTitleIds(Set<Integer> integers) throws TitleServiceException {
        try {
            TitleServiceException te = new TitleServiceException();
            if (integers == null || integers.size() == 0) {
                te.setErrorCode(57);
                throw te;
            }
            List<Additiontitle> list = additiontitleDAO.getTitleListByTitleIds(integers);
            return list;
        } catch (PersistenceException pe) {
            TitleServiceException te = new TitleServiceException(pe);
            te.setErrorCode(50);
            throw te;
        }
    }

    public Title getTitle(Title title) throws TitleServiceException {
        try {
            TitleServiceException te = new TitleServiceException();
            if (title == null) {
                te.setErrorCode(58);
                throw te;
            }
            Title title1 = titleDAO.getTitle(title.getTitleId());
            if (title1 == null) {
                te.setErrorCode(59);
                throw te;
            }
            return title1;
        } catch (PersistenceException pe) {
            TitleServiceException te = new TitleServiceException(pe);
            te.setErrorCode(50);
            throw te;
        }
    }

    public Additiontitle getTitle(Additiontitle title) throws TitleServiceException {
        try {
            TitleServiceException te = new TitleServiceException();
            if (title == null) {
                te.setErrorCode(58);
                throw te;
            }
            Additiontitle title1 = additiontitleDAO.getTitle(title.getTitleId());
            if (title1 == null) {
                te.setErrorCode(59);
                throw te;
            }
            return title1;
        } catch (PersistenceException pe) {
            TitleServiceException te = new TitleServiceException(pe);
            te.setErrorCode(50);
            throw te;
        }
    }

    public int getTitlePageScore(List<Title> titleList) throws TitleServiceException {
        try {
            TitleServiceException te = new TitleServiceException();
            if (titleList == null || titleList.size() == 0) {
                te.setErrorCode(60);
                throw te;
            }
            int score = 0;
            for (int i = 0; i < titleList.size(); i++) {
                if (isTitleCorrect(titleList.get(i))) {
                    score += titleList.get(i).getScore();
                }
            }
            return score;
        } catch (PersistenceException pe) {
            TitleServiceException te = new TitleServiceException(pe);
            te.setErrorCode(50);
            throw te;
        }
    }

    public int getAdditiontitlePageScore(List<Additiontitle> titleList) throws TitleServiceException {
        try {
            TitleServiceException te = new TitleServiceException();
            if (titleList == null || titleList.size() == 0) {
                te.setErrorCode(60);
                throw te;
            }
            int score = 0;
            for (int i = 0; i < titleList.size(); i++) {
                Additiontitle title = getCorrectTitle(titleList.get(i));
                String[] blanks = title.getAnswer().split("#");
                for (int j = 0;j < blanks.length;j++) {
                    // 分数计算方法暂定如下
                    // Score字段对于填空题目是每空的分数
                    // 每题的得分为填对的题目数目*每空的分数
                    score += (Integer.parseInt(blanks[i]) * titleList.get(i).getScore());
                }
            }
            return score;
        } catch (PersistenceException pe) {
            TitleServiceException te = new TitleServiceException(pe);
            te.setErrorCode(50);
            throw te;
        }
    }

    public String getRandomIntegerList(int count) throws TitleServiceException {
        return titleDAO.formatSet(titleDAO.randomIntegerList(count));
    }

    public String getRandomIntegerListByType(int count, int type) throws TitleServiceException {
        return additiontitleDAO.formatSet(additiontitleDAO.randomIntegerList(count, type));
    }

    public List<Title> getTitleListByFormatString(String formatString) throws TitleServiceException {
        try {
            TitleServiceException te = new TitleServiceException();
            if (formatString == null || formatString.trim().equals("")) {
                te.setErrorCode(61);
                throw te;
            }
            return titleDAO.getTitleListByTitleIds(titleDAO.parseString(formatString));
        } catch (PersistenceException pe) {
            TitleServiceException te = new TitleServiceException();
            te.setErrorCode(50);
            throw te;
        }
    }

    public List<Additiontitle> getAdditiontitleListByFormatString(String formatString) throws TitleServiceException {
        try {
            TitleServiceException te = new TitleServiceException();
            if (formatString == null || formatString.trim().equals("")) {
                te.setErrorCode(61);
                throw te;
            }
            return additiontitleDAO.getTitleListByTitleIds(additiontitleDAO.parseString(formatString));
        } catch (PersistenceException pe) {
            TitleServiceException te = new TitleServiceException();
            te.setErrorCode(50);
            throw te;
        }
    }

    public void insertTitle(Title title) throws TitleServiceException {
        try {
            TitleServiceException te = new TitleServiceException();
            if (title == null || title.getOptions() == null) {
                te.setErrorCode(62);
                throw te;
            }
            titleDAO.addTitle(title);
        } catch (PersistenceException pe) {
            TitleServiceException te = new TitleServiceException();
            te.setErrorCode(50);
            throw te;
        }
    }

    public void insertTitle(Additiontitle title) throws TitleServiceException {
        try {
            TitleServiceException te = new TitleServiceException();
            if (title == null) {
                te.setErrorCode(62);
                throw te;
            }
            additiontitleDAO.addTitle(title);
        } catch (PersistenceException pe) {
            TitleServiceException te = new TitleServiceException();
            te.setErrorCode(50);
            throw te;
        }
    }

    public void insertTitleList(List<Title> titleList) throws TitleServiceException {
        try {
            TitleServiceException te = new TitleServiceException();
            if (titleList == null || titleList.size() == 0) {
                te.setErrorCode(63);
                throw te;
            }
            int partSize = 10;
            List<Title> partList = new ArrayList<Title>(partSize);
            for (int i = 0;i < titleList.size();i++) {
                partList.add(titleList.get(i));
                if ((i + 1) % partSize == 0) {
                    titleDAO.addTitleList(partList);
                    partList.clear();
                }
            }
            if (partList.size() > 0) {
                titleDAO.addTitleList(partList);
            }
        } catch (PersistenceException pe) {
            TitleServiceException te = new TitleServiceException();
            te.setErrorCode(63);
            throw te;
        }
    }

    public void insertAdditionTitleList(List<Additiontitle> titleList) throws TitleServiceException {
        try {
            TitleServiceException te = new TitleServiceException();
            if (titleList == null || titleList.size() == 0) {
                te.setErrorCode(63);
                throw te;
            }
            int partSize = 10;
            List<Additiontitle> partList = new ArrayList<Additiontitle>(partSize);
            for (int i = 0;i < titleList.size();i++) {
                partList.add(titleList.get(i));
                if ((i + 1) % partSize == 0) {
                    additiontitleDAO.addTitleList(partList);
                    partList.clear();
                }
            }
            if (partList.size() > 0) {
                additiontitleDAO.addTitleList(partList);
            }
        } catch (PersistenceException pe) {
            TitleServiceException te = new TitleServiceException();
            te.setErrorCode(63);
            throw te;
        }
    }
}
