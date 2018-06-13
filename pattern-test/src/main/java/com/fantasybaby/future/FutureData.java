package com.fantasybaby.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * futureData 给你一个未来的船票
 */
@Slf4j
public class FutureData implements Data {
    protected RealData realData = null;
    protected boolean isReady = false;
    public static ReentrantLock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition();
    public  void setRealData(RealData realData){
        if(isReady){
            return;
        }
        this.realData = realData;
        isReady = true;
        log.info("set Data");
       /* lock.lock();
        condition.signalAll();
        lock.unlock();*/
        synchronized(this){
            notifyAll();
        }

    }
    @Override
    public  String getResult() {
        synchronized(this){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

      /*  lock.lock();
        try {
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }*/
//        while(!isReady){
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            log.info("current ready {}",isReady);
////            try {
//////                wait();
////            } catch (InterruptedException e) {
//////                e.printStackTrace();
////            }
//        }
        return realData.getResult();
    }
}
