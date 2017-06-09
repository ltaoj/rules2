package com.csu.rules.persistence;

import com.csu.rules.domain.Option;
import com.csu.rules.domain.Title;
import com.csu.rules.domain.TitleOption;

import javax.persistence.PersistenceException;
import java.util.List;

/**
 * Created by ltaoj on 17-6-9.
 */
public interface TitleDAO {
    /**
     * @param title
     * @param optionList
     * @throws PersistenceException
     * 添加题目
     */
    void addTitle(Title title, List<Option> optionList) throws PersistenceException;

    /**
     * @param titleOption
     * @throws PersistenceException
     * 添加题目
     */
    void addTitle(TitleOption titleOption) throws PersistenceException;

    /**
     * @param titleId 题目Id
     * @return
     * @throws PersistenceException
     * 根据题目Id删除题目
     */
    TitleOption deleteTitle(int titleId) throws PersistenceException;

    /**
     * @param titleId 题目Id
     * @return
     * @throws PersistenceException
     * 根据题目Id编辑题目
     */
    TitleOption editTitle(int titleId) throws PersistenceException;

    /**
     * @param offset 题目在数据库中的偏移
     * @param count 返回的题目数量
     * @return
     * @throws PersistenceException
     * 根据偏移offset返回数据库中offset个位置后的count个题目
     */
    List<TitleOption> getTitleOptionList(int offset, int count) throws PersistenceException;

    /**
     * @param count
     * @return
     * @throws PersistenceException
     * 随机生成count个题目
     */
    List<TitleOption> getRandomTitleOptionList(int count) throws PersistenceException;

    /**
     * @param titleIds
     * @return
     * @throws PersistenceException
     * 返回titleIds中的所有题目
     */
    List<TitleOption> getTitleOptionListByTitleId(int[] titleIds) throws PersistenceException;
}
