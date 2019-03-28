package com.csu.rules.domain;

import javax.persistence.*;

/**
 * Created by ltaoj on 17-6-8.
 */
@Entity
@Table(name = "signon", schema = "saverulessystem", catalog = "")
@IdClass(SignonPK.class)
public class Signon {
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
}
