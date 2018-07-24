package com.fantasybaby.headparam;

/**
 * -XX:+PrintGCDetails -Xmx20m -Xms5m
 * 分配堆空间
 * @author: liuxi
 * @time: 2018/7/24 11:50
 */
public class HeapParamTest {

    public static void main(String[] args) {
        byte[] bytes = new byte[4*1024*1024];
        System.out.print("Xmx=");
        System.out.println(Runtime.getRuntime().maxMemory()/1024.0/1024+"M");

        System.out.print("free mem=");
        System.out.println(Runtime.getRuntime().freeMemory()/1024.0/1024+"M");

        System.out.print("total mem=");
        System.out.println(Runtime.getRuntime().totalMemory()/1024.0/1024+"M");
        System.gc();
        System.out.print("Xmx=");
        System.out.println(Runtime.getRuntime().maxMemory()/1024.0/1024+"M");

        System.out.print("free mem=");
        System.out.println(Runtime.getRuntime().freeMemory()/1024.0/1024+"M");

        System.out.print("total mem=");
        System.out.println(Runtime.getRuntime().totalMemory()/1024.0/1024+"M");

    }
}
