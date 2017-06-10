package com.csu.rules.persistence;

import com.csu.rules.domain.Notice;
import com.csu.rules.exception.PersistenceException;

import java.util.List;

/**
 * Created by GF on 2017/6/10.
 */
public interface NoticeDAO {
    /**
     * 管理员功能：发布通知
     * @param notice
     */
    void insertNotice(Notice notice) throws PersistenceException;

    /**
     * 根据noticeId查看通知具体内容
     * @param noticeId
     * @return
     */
    Notice getNotice(int noticeId) throws PersistenceException;

    /**
     * 获取文字通知列表
     * @return
     */
    List<Notice> getTextNoticeList() throws PersistenceException;

    /**
     * 获取图片通知列表
     * @return
     */
    List<Notice> getPictrueNoticeList() throws PersistenceException;

    /**
     * 管理员功能：根据noticeId删除通知
     * @param noticeId
     * @throws PersistenceException
     */
    void deleteNotice(int noticeId) throws PersistenceException;

    /**
     * 管理员功能：修改通知内容
     * @param notice
     * @throws PersistenceException
     */
    void updateNotice(Notice notice) throws  PersistenceException;
}
