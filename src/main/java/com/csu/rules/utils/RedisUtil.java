package com.csu.rules.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ltaoj on 2018/05/01 01:28.
 *
 * @version : 1.0
 */
public class RedisUtil {

    public static Map<String, Long> getExpries() {
        Map<String, Long> expires = new HashMap<String, Long>();
        /*单个题目缓存12个小时后过期*/
        expires.put("title", (12 * 60 * 60L));
        expires.put("practice", (1 * 60 * 60L));
        return expires.size() == 0 ? null : expires;
    }
}
