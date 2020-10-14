package com.fantasybaby.dee.locktest;

import com.fantasybaby.dee.code.locktest.LockNotInSameLayer;
import com.fantasybaby.dee.code.locktest.LockingGranularity;
import com.fantasybaby.dee.code.locktest.NoLockAndThreadUnSafe;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

@Slf4j
public class TestNoLockAndThreadUnSafe {
    NoLockAndThreadUnSafe lt;
    LockNotInSameLayer lsl;
    LockingGranularity lg;
    @Before
    public void init() {
        lt = new NoLockAndThreadUnSafe();
        lsl = new LockNotInSameLayer();
        lg = new LockingGranularity();
    }

    @Test
    public void unsafeTest() {
        Thread th = new Thread(() -> lt.add());
        Thread th0 = new Thread(() -> lt.compare());
        th.start();
        th0.start();
        try {
            th.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void notInSameLayer() {
        LockNotInSameLayer.reset();
        IntStream.rangeClosed(0, 100000).parallel().forEach(i -> {
            new LockNotInSameLayer().wrong();
        });
        log.info(LockNotInSameLayer.getCounter() + "");
    }

    @Test
    public void notInSameLayerRight() {
        LockNotInSameLayer.reset();
        IntStream.rangeClosed(0, 100000).parallel().forEach(i -> {
            new LockNotInSameLayer().right();
        });
        log.info(LockNotInSameLayer.getCounter() + "");
    }

    @Test
    public void lockGranularity() {
        lg.wrong();

        lg.right();
    }

}
