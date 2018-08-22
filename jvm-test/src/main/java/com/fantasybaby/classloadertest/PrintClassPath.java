package com.fantasybaby.classloadertest;

/**
 * @author reid.liu
 * @date 2018-08-22 17:37
 */
public class PrintClassPath {
    public void print(){
        System.out.println(System.getProperty("java.class.path"));
        ClassLoader classLoader = PrintClassPath.class.getClassLoader();
        System.out.println(classLoader);

    }
}
