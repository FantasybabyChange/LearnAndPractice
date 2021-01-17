package com.fantasybaby.dee.code.precision;

/**
 * 2021/1/17
 *
 * @authorfantasybaby
 **/
public class DoublePrecision {
    /**
     * 计算机是以二进制存储数值的
     * 和想象不匹配
     * https://en.wikipedia.org/wiki/IEEE_754
     */
    public void notMatchMind() {
        System.out.println(0.1 + 0.2);
        System.out.println(1.0 - 0.8);
        System.out.println(4.015 * 100);
        System.out.println(123.3 / 100);

        double amount1 = 2.15;
        double amount2 = 1.10;
        if (amount1 - amount2 == 1.05) {
            System.out.println("OK");
        }
    }

    public static void main(String[] args) {
        new DoublePrecision().notMatchMind();
    }
}
