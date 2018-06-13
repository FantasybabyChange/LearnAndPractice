package com.fantasybaby.produce;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
@Slf4j
public class Consumer implements  Runnable{
    private BlockingQueue<String> queue = null;
    public Consumer(BlockingQueue queue){
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true){
            log.info("start consumer");
            try {
                Thread.sleep(1000);
                String poll = queue.poll(1000, TimeUnit.SECONDS);
                log.info("task poll {}" ,poll);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
