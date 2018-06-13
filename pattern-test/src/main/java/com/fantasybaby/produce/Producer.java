package com.fantasybaby.produce;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable{
    private BlockingQueue<String> queue ;
    private String name;
    public Producer(BlockingQueue queue,String name){
        this.queue = queue;
        this.name = name;
    }
    @Override
    public void run() {
        int i = 0;
        while (true){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            queue.offer(name + "-" + Thread.currentThread().getName() + ":" +i);
            i++;

        }

    }
}
