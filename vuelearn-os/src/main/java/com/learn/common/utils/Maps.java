package com.learn.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * All Rights Reserved.
 *
 */
public class Maps<T> {

    public static <T> Map<String, Object> create(Object... keyValues){
        return create(String.class, keyValues);
    }

    public static <T> Map<T, Object> create(Class<T> keyType, Object... keyValues){
        Map<T, Object> map = new HashMap<T, Object>();
        for (int i = 0; i < keyValues.length; i++) {
            map.put((T) keyValues[i], keyValues[i+1]);
            i++;
        }
        return map;
    }
}