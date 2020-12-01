package com.fantasybaby.study.java8test.change;

import java.util.function.Supplier;

/**
 * @author liuxi
 * @date2018年02月06日 16:08
 */
public class ObjectFactory {
    public static <T> T  create(Supplier<T> userBean){
        return userBean.get();
    }
}
