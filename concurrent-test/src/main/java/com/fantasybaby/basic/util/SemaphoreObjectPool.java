package com.fantasybaby.basic.util;/******************************************************************************
 * Copyright (C) 2018 ShangHai Quicktron Intelligent Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为上海快仓智能科技开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 * 版权所有 翻版必究 --liuxi
 *****************************************************************************/

import java.util.List;
import java.util.Vector;
import java.util.concurrent.Semaphore;
import java.util.function.Function;

/**
 * 使用信号量实现限流
 * @author: liuxi
 * @time: 2019/9/18 19:41
 */
public class SemaphoreObjectPool<T, R> {
        final List<T> pool;
        // 用信号量实现限流器
        final Semaphore sem;
        // 构造函数
        SemaphoreObjectPool(int size, T t){
            pool = new Vector<T>(){};
            for(int i=0; i<size; i++){
                pool.add(t);
            }
            sem = new Semaphore(size);
        }
        // 利用对象池的对象，调用 func
        R exec(Function<T,R> func) throws InterruptedException {
            T t = null;
            sem.acquire();
            try {
                t = pool.remove(0);
                return func.apply(t);
            } finally {
                pool.add(t);
                sem.release();
            }
        }

    public static void main(String[] args) {
        // 创建对象池 限流
        SemaphoreObjectPool<Long, String> pool =
                new SemaphoreObjectPool(3, 2L);
    // 通过对象池获取 t，之后执行
            for (int i = 0; i < 10; i++) {
                new Thread(()->{
                    try {
                        pool.exec(t -> {
                            System.out.println("start--" + t);
                            try {
                                Thread.sleep(2000L);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.out.println("end--" + t);
                            return t.toString();
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }}
                ).start();
            }
    }

}
