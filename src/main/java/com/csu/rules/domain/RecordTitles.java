package com.csu.rules.domain;

import java.util.List;

/**
 * Created by GF on 2017/6/25.
 */
public class RecordTitles {
    private Testrecord testrecord;
    private List<Title> titleList;

    public Testrecord getTestrecord() {
        return testrecord;
    }

    public void setTestrecord(Testrecord testrecord) {
        this.testrecord = testrecord;
    }

    public List<Title> getTitleList() {
        return titleList;
    }

    public void setTitleList(List<Title> titleList) {
        this.titleList = titleList;
    }
}
