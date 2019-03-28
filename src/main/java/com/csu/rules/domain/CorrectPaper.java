package com.csu.rules.domain;

/**
 * Created by GF on 2017/8/17.
 */
public class CorrectPaper {
    private String studentId;
    private int testId;
    private int score;

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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
