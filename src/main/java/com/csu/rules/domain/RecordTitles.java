package com.csu.rules.domain;

import java.util.List;

/**
 * Created by GF on 2017/6/25.
 */
public class RecordTitles {
    private Testrecord testrecord;
    private submitPaper submitPaper;

    public Testrecord getTestrecord() {
        return testrecord;
    }

    public void setTestrecord(Testrecord testrecord) {
        this.testrecord = testrecord;
    }

    public com.csu.rules.domain.submitPaper getSubmitPaper() {
        return submitPaper;
    }

    public void setSubmitPaper(com.csu.rules.domain.submitPaper submitPaper) {
        this.submitPaper = submitPaper;
    }
}
