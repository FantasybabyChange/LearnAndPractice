package com.fantasybaby.test.pool;

import com.fantasybaby.basic.threadpool.forkjoin.ExecutoServiceCalculatior;
import com.fantasybaby.basic.threadpool.forkjoin.ForkJoinFeedBack;
import org.junit.Test;

import java.util.stream.LongStream;

public class ForkJoinTest {
    LongStream longStream = LongStream.rangeClosed(1, 100);
    /**
     * 通过线程池来完成分治
     */
    @Test
    public void test1(){

        long sum = new ExecutoServiceCalculatior().sumUp(longStream.toArray());
        System.out.println(sum);
    }
    @Test
    public void test2(){
        long sum = new ForkJoinFeedBack().sumUp(longStream.toArray());
        System.out.println(sum);
        int a = 0x7fff;
        System.out.println(a);
    }
}
