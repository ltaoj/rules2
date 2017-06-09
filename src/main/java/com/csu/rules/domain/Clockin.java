package com.csu.rules.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by CMM on 2017/6/9.
 */
@Entity
public class Clockin {
    private String comment;
    private int clockId;
    private Timestamp clockDay;
    private Integer duration;
    private Integer titleNum;
    private Long studentId;

    @Basic
    @Column(name = "comment")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Id
    @Column(name = "clock_id")
    public int getClockId() {
        return clockId;
    }

    public void setClockId(int clockId) {
        this.clockId = clockId;
    }

    @Basic
    @Column(name = "clock_day")
    public Timestamp getClockDay() {
        return clockDay;
    }

    public void setClockDay(Timestamp clockDay) {
        this.clockDay = clockDay;
    }

    @Basic
    @Column(name = "duration")
    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @Basic
    @Column(name = "title_num")
    public Integer getTitleNum() {
        return titleNum;
    }

    public void setTitleNum(Integer titleNum) {
        this.titleNum = titleNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Clockin clockin = (Clockin) o;

        if (clockId != clockin.clockId) return false;
        if (comment != null ? !comment.equals(clockin.comment) : clockin.comment != null) return false;
        if (clockDay != null ? !clockDay.equals(clockin.clockDay) : clockin.clockDay != null) return false;
        if (duration != null ? !duration.equals(clockin.duration) : clockin.duration != null) return false;
        if (titleNum != null ? !titleNum.equals(clockin.titleNum) : clockin.titleNum != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = comment != null ? comment.hashCode() : 0;
        result = 31 * result + clockId;
        result = 31 * result + (clockDay != null ? clockDay.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        result = 31 * result + (titleNum != null ? titleNum.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "student_id")
    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
}
