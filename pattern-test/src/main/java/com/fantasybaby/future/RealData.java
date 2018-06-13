package com.fantasybaby.future;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RealData implements Data{
    private final String result;
    public RealData(String para){
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            sb.append(para);
            try {
                log.info("start sleep");
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        result = sb.toString();
    }
    @Override
    public String getResult() {
        return result;
    }
}
