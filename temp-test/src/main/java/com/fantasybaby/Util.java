package com.fantasybaby;

import java.util.function.Supplier;

import com.google.common.collect.Lists;

/**
 * @author liuxi
 * @date2018年05月31日 13:58
 */
public class Util {
    //测试
    public static void main(String[] args) {
        Lists.newArrayList();
    }
    public static String hello(){
        Supplier<String> s = () -> "2";
        return s.get();
    }
}
