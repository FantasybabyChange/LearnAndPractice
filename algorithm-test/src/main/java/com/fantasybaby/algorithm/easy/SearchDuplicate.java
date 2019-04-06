package com.fantasybaby.algorithm.easy;

/** 1到n个数字中只有m会重复出现,请找出来
 * @author fantasybaby
 * @date 2019/4/6
 */
public class SearchDuplicate {
    /**
     * a^b^c^m^m^..n
     * ^
     * a^b^c^m^..n
     * ^
     * m
     * @param oArr
     * @param n
     * @return
     */
    public static int getSpecialNum(int[] oArr, int n){
        int result = 0;
        for(int i = 0; i < oArr.length; i++){
            result = (result^oArr[i]);
        }
        for(int j = 1; j <= n; j++){
            result = result^j;
        }
        return result;
    }
    public static void main(String[] args) {
        int[] a = {1,2,3,4,5,5,6};
        int specialNum = new SearchDuplicate().getSpecialNum(a, 6);
        System.out.println(specialNum);
    }
}
