package com.csu.rules.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by ltaoj on 17-6-9.
 */
public class IntegralPK implements Serializable {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IntegralPK that = (IntegralPK) o;

        if (studentId != that.studentId) return false;
        if (integralNum != null ? !integralNum.equals(that.integralNum) : that.integralNum != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 31 * studentId.hashCode();
        result = 31 * result + (integralNum != null ? integralNum.hashCode() : 0);
        return result;
    }
}
