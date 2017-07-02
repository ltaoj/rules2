package com.csu.rules.domain;

import javax.persistence.*;

/**
 * Created by ltaoj on 17-7-2.
 */
@Entity
@Table(name = "testtitle", schema = "schoolrulessystem", catalog = "")
@IdClass(TesttitlePK.class)
public class Testtitle {
    @Id
    @Column(name = "student_id")
    private long studentId;
    @Id
    @Column(name = "test_id")
    private int testId;
    @Column(name = "title_ids")
    private String titleIds;

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public String getTitleIds() {
        return titleIds;
    }

    public void setTitleIds(String titleIds) {
        this.titleIds = titleIds;
    }
}
