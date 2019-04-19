package com.csu.rules.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by ltaoj on 17-6-9.
 */
public class ContestregistionPK implements Serializable {
    @Id
    @Column(name = "student_id")
    private String studentId;
    @Id
    @Column(name = "test_id")
    private int testId;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContestregistionPK that = (ContestregistionPK) o;

        if (studentId != that.studentId) return false;
        if (testId != that.testId) return false;

        return true;
    }


    @Override
    public int hashCode() {
        return 31 * studentId.hashCode();
    }
}