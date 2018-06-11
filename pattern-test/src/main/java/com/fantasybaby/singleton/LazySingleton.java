package com.fantasybaby.singleton;

/**
 * 通过同步块懒加载单例实例
 *
 * @author liuxi
 * @date 2018-06-11
 */
public class LazySingleton {
    private LazySingleton() {
        System.out.println("LazySingleton is create");
    }

    private static LazySingleton instance = null;

    public static synchronized LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}