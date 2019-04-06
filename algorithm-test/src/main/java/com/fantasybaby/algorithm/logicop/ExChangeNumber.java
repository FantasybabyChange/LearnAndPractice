package com.fantasybaby.algorithm.logicop;

/**
 * @author fantasybaby
 * @date 2019/4/6
 */
public class ExChangeNumber {
    /**
     * 通过异或去交换两个数
     * 原理
     * a = a^b;
     * b=a^b=a^b^b=a^0=a
     * a=a^b=a^b^a=0^b=b
     * @param a
     * @param b
     */
    public void exchangeSideWithoutThirdVar(int a , int b){
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println(a+":"+b);
    }

    public static void main(String[] args) {
        new ExChangeNumber().exchangeSideWithoutThirdVar(10,11);
    }
}
