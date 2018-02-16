package com.fantasybaby.normaltest.java8test.change;

import java.lang.annotation.Annotation;

/**
 * @author fantasybaby
 * @date 2018/2/16
 */
@ChildAnno("hello1")
@ChildAnno("hello2")
public class DuplicateAnnotationTest {
    public static void main(String[] args) {
        ParentAnno annotation = DuplicateAnnotationTest.class.getAnnotation(ParentAnno.class);
        ChildAnno[] value = annotation.value();
        for (ChildAnno childAnno : value) {
            System.out.println(childAnno.value());
        }
        /*ChildAnno[] value = annotation1.value();
        for (ChildAnno childAnno : value) {
            System.out.println(childAnno.value());
        }*/
    }
}