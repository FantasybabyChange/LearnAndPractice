package com.fantasybaby.gc;

import java.util.HashMap;

/**
 *
 * -Xmx600M -Xms512M -XX:+UseSerialGC -Xloggc:gc.log -XX:+PrintGCDetails  -Xmn1m -XX:PretenureSizeThreshold=50 -XX:MaxTenuringThreshold=1
 * @author: liuxi
 * @time: 2018/7/30 17:00
 */
public class StopTheWorldTest {
    public static Long startSystemMillion = System.currentTimeMillis();
    public static class T1 extends Thread{

        @Override
        public void run() {
            while (true){
                System.out.println("time" + (System.currentTimeMillis() - startSystemMillion));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static class T2 extends Thread{
        HashMap<Long,byte[]> map=new HashMap();
        @Override
        public void run() {
            try{
                while(true){
                    if(map.size()*512/1024/1024>=450){
                        System.out.println("=====准备清理=====:"+map.size());
                        map.clear();
                    }

                    for(int i=0;i<1024;i++){
                        map.put(System.nanoTime(), new byte[512]);
                    }
                    Thread.sleep(1);
                }
            }catch(Exception e){
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        new T1().start();
        new T2().start();
    }
}
