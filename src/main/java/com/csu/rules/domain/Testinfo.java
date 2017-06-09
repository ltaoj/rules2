package com.csu.rules.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by GF on 2017/6/8.
 */
@Entity
public class Testinfo {
    private String name;
    private byte type;
    private int grade;
    private int testId;
    private Timestamp startTime;
    private Timestamp endTime;
    private int duration;

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "type")
    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    @Basic
    @Column(name = "grade")
    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
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
    @Column(name = "start_time")
    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "end_time")
    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "duration")
    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Testinfo testinfo = (Testinfo) o;

        if (type != testinfo.type) return false;
        if (grade != testinfo.grade) return false;
        if (testId != testinfo.testId) return false;
        if (duration != testinfo.duration) return false;
        if (name != null ? !name.equals(testinfo.name) : testinfo.name != null) return false;
        if (startTime != null ? !startTime.equals(testinfo.startTime) : testinfo.startTime != null) return false;
        if (endTime != null ? !endTime.equals(testinfo.endTime) : testinfo.endTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (int) type;
        result = 31 * result + grade;
        result = 31 * result + testId;
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + duration;
        return result;
    }
}
