package com.csu.rules.persistence;

import com.csu.rules.domain.Paperrecord;
import com.csu.rules.exception.PersistenceException;

/**
 * Created by ltaoj on 17-7-17.
 */
public interface PaperrecordDAO {

    /**
     * 将提交试卷写进数据库
     * @param paperrecord
     * @return
     * @throws PersistenceException
     */
    Integer insertPaperrecord(Paperrecord paperrecord) throws PersistenceException;

    /**
     * 通过试卷编号删除提交的试卷
     * @param paperId
     * @throws PersistenceException
     */
    void deletePaperrecord(int paperId) throws PersistenceException;

    /**
     * 通过学号删除所有试卷
     * @param studentId
     * @throws PersistenceException
     */
    void deletePaperrecordByStudentId(int studentId) throws PersistenceException;

    /**
     * 通过测试编号删除所有试卷
     * @param testId
     * @throws PersistenceException
     */
    void deletePaperrecordByTestId(int testId) throws PersistenceException;

    /**
     * 通过学号和测试编号删除试卷
     * @param studentId
     * @param testId
     * @throws PersistenceException
     */
    void deletePaperrecordByStudentIdAndTestId(int studentId, int testId) throws PersistenceException;

    /**
     * 通过试卷编号获取试卷
     * @param paper_id
     * @return
     * @throws PersistenceException
     */
    Paperrecord getPaperrecord(int paper_id) throws PersistenceException;

    /**
     * 通过学号和测试编号获取试卷
     * @param studentId
     * @param testId
     * @return
     * @throws PersistenceException
     */
    Paperrecord getPaperrecordByStudentIdAndTestId(int studentId, int testId) throws PersistenceException;

    /**
     * 通过学号获取试卷
     * @param studentId
     * @return
     * @throws PersistenceException
     */
    Paperrecord getPaperrecordByStudentId(int studentId) throws PersistenceException;

    /**
     * 通过试卷编号更新提交的试卷
     * @param paperId
     * @param paperrecord
     * @throws PersistenceException
     */
    void updatePaperrecord(int paperId, Paperrecord paperrecord) throws PersistenceException;

    /**
     * 通过学号和测试编号更新试卷
     * @param studentId
     * @param testId
     * @param paperrecord
     * @throws PersistenceException
     */
    void updatePaperrecordBy(int studentId, int testId, Paperrecord paperrecord) throws PersistenceException;
}
