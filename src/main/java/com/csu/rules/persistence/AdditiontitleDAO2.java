package com.csu.rules.persistence;

import com.csu.rules.exception.PersistenceException;

import java.util.List;

/**
 * Created by ltaoj on 2018/05/01 14:04.
 *
 * @version : 1.0
 */
public interface AdditiontitleDAO2 {

    /**
     * 获取所有附加题库id集合，可缓存
     * @param type
     * @return
     * @throws PersistenceException
     */
    List<Integer> getAdditiontitleIdsByType(int type) throws PersistenceException;
}
