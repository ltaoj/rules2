package com.csu.rules.domain;

import java.util.List;

/**
 * Created by ltaoj on 17-6-12.
 */
public class AccountTitles {
    private Account account;
    private List<Title> titleList;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<Title> getTitleList() {
        return titleList;
    }

    public void setTitleList(List<Title> titleList) {
        this.titleList = titleList;
    }
}
