package com.csu.rules.domain;

import javax.persistence.*;

/**
 * Created by ltaoj on 17-6-9.
 */
@Entity
@Table(name = "integral", schema = "saverulessystem", catalog = "")
@IdClass(IntegralPK.class)
public class Integral {
    @Id
    @Column(name = "student_id")
    private String studentId;
    @Id
    @Column(name = "integral_num")
    private Integer integralNum;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Integer getIntegralNum() {
        return integralNum;
    }

    public void setIntegralNum(Integer integralNum) {
        this.integralNum = integralNum;
    }
}
