package com.fantasybaby.context;

/**
 * @author reid.liu
 * @date 2018-09-20 19:28
 */
public class ThreadLocalContext {
    private static ThreadLocal<String> currentFile = new ThreadLocal<>();
    public static void  set(String fileName){
        currentFile.set(fileName);
    }
    public static String get(){
        return currentFile.get();
    }
}
