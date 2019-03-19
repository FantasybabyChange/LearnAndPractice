package com.fantasybaby.algorithm.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 水仙花数
 * @author: liuxi
 * @time: 2019/3/19 13:22
 */
public class NarcissisticNumbers {
    /**
     * @param n: The number of digits
     * @return: All narcissistic numbers with n digits
     */
    public static List<Integer> getNarcissisticNumbers(int n) {
        List<Integer> result = new ArrayList();
        int start = (int) Math.pow(10,n-1)-1;
        while(start < Math.pow(10,n)){
            int tmp = start;
            int end = 0;
            while(tmp > 0){
                end+=Math.pow(tmp%10,n);
                tmp = tmp/10;
            }
            if(start == end){
                result.add(start);
            }
            start++;

        }
        return  result;
    }

    public static void main(String[] args) {
        List<Integer> narcissisticNumbers = NarcissisticNumbers.getNarcissisticNumbers(3);
        System.out.println(narcissisticNumbers);
    }
}
