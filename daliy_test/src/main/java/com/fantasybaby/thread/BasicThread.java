package com.fantasybaby.thread;

import java.util.concurrent.LinkedBlockingQueue;

public class BasicThread {
    public static void main(String[] args) throws InterruptedException {
        final LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
        // 创建一个线程
        Thread thread = new Thread(() -> {
            try {
                System.out.println("hi hello");
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        // 启动线程
        thread.start();
        // 主线程挂起，保证thread线程逻辑进入并执行
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 主线程向队列中塞一个数据，唤醒thread线程
        queue.put("hello world");
        System.out.println("out hello world");
        // 等待线程执行完毕
//        thread.join();
        // 线程执行结束
        System.out.println("---over---");
    }
}
