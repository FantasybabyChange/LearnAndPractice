package com.fantasybaby.future;

import lombok.extern.slf4j.Slf4j;

/**
 * futureData 给你一个未来的船票
 */
@Slf4j
public class FutureData implements Data {
    protected RealData realData = null;
    protected boolean isReady = false;
    public synchronized void setRealData(RealData realData){
        if(isReady){
            return;
        }
        this.realData = realData;
        isReady = true;
        notifyAll();
    }
    @Override
    public String getResult() {
        while(!isReady){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return realData.getResult();
    }
}
