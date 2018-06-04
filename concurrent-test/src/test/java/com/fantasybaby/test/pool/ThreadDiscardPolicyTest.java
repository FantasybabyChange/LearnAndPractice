package com.fantasybaby.test.pool;

import com.fantasybaby.basic.threadpool.ThreadDiscardPolicy;
import org.junit.Before;
import org.junit.Test;

public class ThreadDiscardPolicyTest {
    ThreadDiscardPolicy threadDiscard;
    @Before
    public void init(){
        threadDiscard = new ThreadDiscardPolicy();
    }

    /**
     * 线程池直接拒绝
     */
    @Test
    public void testDiscardPolicy(){
        threadDiscard.discardPolicy();
    }
}
