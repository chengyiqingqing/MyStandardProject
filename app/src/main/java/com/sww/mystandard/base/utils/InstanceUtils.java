package com.sww.mystandard.base.utils;

import java.lang.reflect.ParameterizedType;

public class InstanceUtils {

    public static final int POSITION_FIRST = 0;

    public static <T> T getInstance(Object object, int positon) {
        try {
            return ((Class<T>) ((ParameterizedType) object.getClass()
                        .getGenericSuperclass())
                        .getActualTypeArguments()[POSITION_FIRST]) // 该方法属于ParameterizedType泛型类
                        .newInstance(); // 该方法属于Class对象
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Class<?> getClassForName(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
