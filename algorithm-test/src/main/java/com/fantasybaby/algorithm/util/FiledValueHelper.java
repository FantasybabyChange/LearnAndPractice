/******************************************************************************
 * Copyright (C) 2018 ShangHai Quicktron Intelligent Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为上海快仓智能科技开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.fantasybaby.algorithm.util;

import com.google.common.collect.Lists;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;

/**
 * @author reid.liu
 * @date 2019-02-25 11:38
 */
public class FiledValueHelper {
    /**
     * 根据属性名获取属性值
     *
     * @param fieldName
     * @param object
     * @return
     */
    public  <T> T getAllFieldValueByFieldName(String fieldName, Object object) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            //设置对象的访问权限，保证对private的属性的访问
            field.setAccessible(true);
            return  (T)field.get(object);
        } catch (Exception e) {
        }
        return null;
    }
    /**
     * 根据属性名获取属性值
     *
     * @param fieldName
     * @param object
     * @return
     *
     */
    public static void setFieldValueByFieldName(String fieldName, String value,Object object) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            //设置对象的访问权限，保证对private的属性的访问
            field.setAccessible(true);
            field.set(object,value);
        } catch (Exception e) {
        }
    }
    /**
     * 根据属性名获取属性元素，包括各种安全范围和所有父类
     *
     * @param fieldName
     * @param object
     * @return
     */
    public static <T> T getFieldByClasss(String fieldName, Object object) {
        Field field = null;
        Class<?> clazz = object.getClass();

        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                return  (T)field.get(object);
            } catch (Exception e) {
            }
        }
        return null;
    }

    public static void main(String[] args) {

    }
}
