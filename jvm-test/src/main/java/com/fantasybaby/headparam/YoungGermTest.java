package com.fantasybaby.headparam;

/**
 * -XX:+UseSerialGC 使用串型垃圾器
 * -Xmx20m -Xms20m -Xmn1m  -XX:+PrintGCDetails
 * -Xmx20m -Xms20m -Xmn15m  -XX:+PrintGCDetails
 * -Xmx20m -Xms20m -Xmn7m  -XX:+PrintGCDetails
 * -Xmx20m -Xms20m -Xmn7m  -XX:+PrintGCDetails
 * -Xmx20m -Xms20m -Xmn7m  -XX:+PrintGCDetails -XX:SurvivorRatio=2
 * -Xmx20m -Xms20m  -XX:+PrintGCDetails -XX:SurvivorRatio=2 -XX:NewRatio=1
 * @author: liuxi
 * @time: 2018/7/24 15:45
 */
public class YoungGermTest {
    public static void main(String[] args) {
        byte[] bytes =null;
        for (int i = 0; i < 10; i++) {
            bytes = new byte[1*1024*1024];
        }

    }
}
