package com.csu.rules.domain;

import javax.persistence.*;

/**
 * Created by GF on 2017/6/8.
 */
@Entity
@IdClass(ContestregistionPK.class)
public class Contestregistion {
    private long studentId;
    private int testId;
    private int status;

    @Id
    @Column(name = "student_id")
    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    @Id
    @Column(name = "test_id")
    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    @Basic
    @Column(name = "status")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contestregistion that = (Contestregistion) o;

        if (studentId != that.studentId) return false;
        if (testId != that.testId) return false;
        if (status != that.status) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (studentId ^ (studentId >>> 32));
        result = 31 * result + testId;
        result = 31 * result + status;
        return result;
    }
}
