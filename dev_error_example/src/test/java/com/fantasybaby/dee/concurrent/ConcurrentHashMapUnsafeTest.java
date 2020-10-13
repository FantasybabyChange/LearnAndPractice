package com.fantasybaby.dee.concurrent;

import com.fantasybaby.dee.code.concurrent.ConcurrentHashMapUnsafe;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

public class ConcurrentHashMapUnsafeTest {
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
    @Test
    public void testLockAdd(){
        try {
            Map<String, Long> stringLongMap = map.useLockToAdd();
            System.out.println(stringLongMap.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testUnlockAdd(){
        try {
            Map<String, Long> stringLongMap = map.casToAdd();
            System.out.println(stringLongMap.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
