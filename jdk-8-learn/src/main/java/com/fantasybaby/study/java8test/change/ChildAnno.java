package com.fantasybaby.study.java8test.change;

import java.lang.annotation.*;

/**
 * child annotation
 * @author fantasybaby
 * @date 2018/2/16
 */
//父类注解可以使用
@Repeatable(ParentAnno.class)
@interface ChildAnno {
    String value();
}
