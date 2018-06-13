package com.fantasybaby.future;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Client {
    public Data request(String queryStr){
        final FutureData future = new FutureData();
        new Thread(()->{
            RealData realData = new RealData(queryStr);
            future.setRealData(realData);
        }).start();
        return future;
    }

    public static void main(String[] args) {
        Client cl = new Client();
        Data resultData = cl.request("hello future");
        log.info("request over");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("real result " + resultData.getResult());
    }
}
