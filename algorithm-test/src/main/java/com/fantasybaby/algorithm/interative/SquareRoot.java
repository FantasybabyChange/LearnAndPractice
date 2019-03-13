package com.fantasybaby.algorithm.interative;

/**
 * @author liuxi
 */
public class SquareRoot {
    /**
     *
     * @param n  求平方的数
     * @param deltaThreshold  //精确度阈值
     * @param maxTry  最大尝试数
     * @return
     */
    public static  double calculateSquare(int n,double deltaThreshold,int maxTry){
        if(n < 0){
            return  -1;
        }
        double min=1.0,max=n;
        for (int i = 0; i < maxTry; i++) {
//            double v = (min + max) / 2;  这里有可能导致溢出
            double middle = max - (max - min) / 2; //这样不会溢出
            double doubleMiddle = middle * middle;
            if(Math.abs((doubleMiddle-n)/n) <= deltaThreshold){//相对误差和阈值做对比
                return middle;
            }
            if(doubleMiddle < n){
                min = middle;
            }else{
                max = middle;
            }
        }
        return 0;
    }
    /**
     * @Description: 查找某个单词是否在字典里出现
     * @param dictionary- 排序后的字典, wordToFind- 待查的单词
     * @return boolean- 是否发现待查的单词
     */
    public static boolean search(String[] dictionary, String wordToFind) {
        if (dictionary == null) {
            return false;
        }
        if (dictionary.length == 0) {
            return false;
        }
        int left = 0, right = dictionary.length - 1;
        while (left <= right) {
            int middle = (left + right) / 2;
            if (dictionary[middle].equals(wordToFind)) {
                return true;
            } else {
                if (dictionary[middle].compareTo(wordToFind) > 0) {
                    right = middle - 1;
                } else {
                    left = middle + 1;
                }
            }
        }
        return false;
    }


    public static void main(String[] args) {
        double v = SquareRoot.calculateSquare(10, 0.001, 100000);
        System.out.println(v);
    }

}
