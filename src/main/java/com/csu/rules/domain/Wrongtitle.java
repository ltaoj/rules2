package com.csu.rules.domain;

import javax.persistence.*;

/**
 * Created by ltaoj on 17-6-9.
 */
@Entity
@Table(name = "wrongtitle", schema = "schoolrulessystem", catalog = "")
@IdClass(WrongtitlePK.class)
public class Wrongtitle {
    @Id
    @Column(name = "title_id")
    private Integer titleId;
    @Id
    @Column(name = "student_id")
    private long studentId;

    public Integer getTitleId() {
        return titleId;
    }

    public void setTitleId(Integer titleId) {
        this.titleId = titleId;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }
}
