package com.csu.rules.domain;

import javax.persistence.*;

/**
 * Created by ltaoj on 17-7-2.
 */
@Entity
@Table(name = "contesttitle", schema = "schoolrulessystem", catalog = "")
public class Contesttitle {
    @Id
    @Column(name = "test_id")
    private int testId;
    @Column(name = "title_ids")
    private String titleIds;

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public String getTitleIds() {
        return titleIds;
    }

    public void setTitleIds(String titleIds) {
        this.titleIds = titleIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contesttitle that = (Contesttitle) o;

        if (testId != that.testId) return false;
        if (titleIds != null ? !titleIds.equals(that.titleIds) : that.titleIds != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = testId;
        result = 31 * result + (titleIds != null ? titleIds.hashCode() : 0);
        return result;
    }
}
