package com.fantasybaby.study.java8test.change;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 集合annotation
 * @author fantasybaby
 * @date 2018/2/16
 */
@Retention(RetentionPolicy.RUNTIME)
@interface ParentAnno {
        ChildAnno[] value();
}
