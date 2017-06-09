package com.csu.rules.domain;

import java.util.List;

/**
 * Created by ltaoj on 17-6-9.
 */
public class TitleOption {
    private int titleId;
    private String name;
    private int diffId;
    private int score;
    private List<Option> optionList;

    public int getTitleId() {
        return titleId;
    }

    public void setTitleId(int titleId) {
        this.titleId = titleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDiffId() {
        return diffId;
    }

    public void setDiffId(int diffId) {
        this.diffId = diffId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<Option> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<Option> optionList) {
        this.optionList = optionList;
    }
}
