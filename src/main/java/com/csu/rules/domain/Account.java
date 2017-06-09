package com.csu.rules.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by ltaoj on 17-6-8.
 */
@Entity
@Table(name = "userinfo", schema = "schoolrulessystem", catalog = "")
public class Account {
    @Id
    @Column(name = "student_id")
    private long studentId;
    @Column(name = "username")
    private String username;
    @Column(name = "sex")
    private byte sex;
    @Column(name = "class")
    private String clazz;
    @Column(name = "grade")
    private int grade;
    @Column(name = "college")
    private String college;
    @Column(name = "school")
    private String school;

    public Account() {}

    public Account(long studentId, String username, byte sex, String clazz, int grade, String college) {
        this.studentId = studentId;
        this.username = username;
        this.sex = sex;
        this.clazz = clazz;
        this.grade = grade;
        this.college = college;
    }

    public Account(long studentId, String username, byte sex, String clazz, int grade, String college, String school) {
        this.studentId = studentId;
        this.username = username;
        this.sex = sex;
        this.clazz = clazz;
        this.grade = grade;
        this.college = college;
        this.school = school;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public byte getSex() {
        return sex;
    }

    public void setSex(byte sex) {
        this.sex = sex;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }
}