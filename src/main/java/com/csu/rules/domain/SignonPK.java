package com.csu.rules.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by ltaoj on 17-6-8.
 */
public class SignonPK implements Serializable {
    @Id
    @Column(name = "student_id")
    private String studentId;
    @Id
    @Column(name = "password")
    private String password;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SignonPK that = (SignonPK) o;

        if (studentId != that.studentId) return false;
        if (password != null ? !password .equals(that.password) : that.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 31 * studentId.hashCode();
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
