package com.fantasybaby.dee.concurrent;

import com.fantasybaby.dee.code.concurrent.ConcurrentHashMapUnsafe;
import org.junit.Before;
import org.junit.Test;

public class ConcurrentHashMapTest {
    ConcurrentHashMapUnsafe map;
    @Before
    public void init(){
        map = new ConcurrentHashMapUnsafe();
    }
    @Test
    public void testWrong(){
        try {
            map.unsafePut();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    @Test
    public void testSafe(){
        try {
            map.safePut();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
