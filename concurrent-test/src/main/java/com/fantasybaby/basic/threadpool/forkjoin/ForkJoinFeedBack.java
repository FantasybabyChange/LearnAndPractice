package com.fantasybaby.basic.threadpool.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * fork join 有返回
 */
public class ForkJoinFeedBack extends RecursiveTask<Integer>{
    @Override
    protected Integer compute() {
        return 1;
    }
}
