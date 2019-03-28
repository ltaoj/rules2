package com.csu.rules.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ltaoj on 17-6-9.
 */
@Entity
@Table(name = "wrongtitle", schema = "saverulessystem", catalog = "")
@IdClass(WrongtitlePK.class)
public class Wrongtitle implements Serializable {
    @Id
    @Column(name = "title_id")
    private Integer titleId;
    @Id
    @Column(name = "student_id")
    private String studentId;

    public Integer getTitleId() {
        return titleId;
    }

    public void setTitleId(Integer titleId) {
        this.titleId = titleId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
