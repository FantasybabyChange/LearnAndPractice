package com.fantasybaby.produce;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 通过阻塞队列实现生产消费
 */
public class ConsumerTest {
    public static void main(String[] args) {
        Producer p1 = new Producer(QueueHandle.messageQueue,"gukong");
        Producer p2 = new Producer(QueueHandle.messageQueue,"saka");
        Producer p3 = new Producer(QueueHandle.messageQueue,"kukusa");
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.submit(p1);
        executorService.submit(p2);
        executorService.submit(p3);
        Consumer consumer = new Consumer(QueueHandle.messageQueue);
        executorService.submit(consumer);
    }
}
