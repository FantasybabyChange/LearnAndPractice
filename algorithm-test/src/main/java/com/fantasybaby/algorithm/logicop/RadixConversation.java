package com.fantasybaby.algorithm.logicop;

import java.math.BigInteger;

/**
 * @author reid.liu
 * @date 2019-03-05 19:21
 */
public class RadixConversation {
    /**
     * 使用位运算
     * 处理正整数的二进制转换
     * @param value
     * @return
     */
    public static String decimalToBinary(int value){
        StringBuffer sb = new StringBuffer();
        while (value >0){
            sb.append(value & 1) ;
            value = value>>1;
        }
        return sb.reverse().toString();
    }
    public static int binaryToDecimal(int binaryNumber){

        int decimal = 0;
        int p = 0;
        while(true){
            if(binaryNumber == 0){
                break;
            } else {
                int temp = binaryNumber%10;
                decimal += temp*Math.pow(2, p);
                binaryNumber = binaryNumber/10;
                p++;
            }
        }
        return decimal;
    }

    /**
     * 负整数转换为二进制 要点：
     * 取反加一 解释：将该负整数对应的正整数先转换成二进制，然后对其“取补”，再对取补后的结果加1即可。
     * 例如要把-52换算成二进制：
     * 1.先取得52的二进制：00110100
     * 2.对所得到的二进制数取反：1101011
     * 3.将取反后的数值加一即可：1101100 即：(-52)10=(1101100)2
     * @param value
     * @return
     */
    public static String decimalToBinaryNegative(int value){
        StringBuffer sb = new StringBuffer();
        value = ~value;
        value = value+1;
        while (value >0){
            sb.append(value & 1) ;
            value = value>>1;
        }
        //给高位加1
        sb.append(1);
        return sb.reverse().toString();
    }
    /**
     * @Title: decimalToBinary
     * @Description: 十进制转二进制，方法1：余数短除法除以二
     * @param decimalSource
     * @return: String
     */
  /*  public static String decimalToBinary(int decimalSource) {
        StringBuilder sb = new StringBuilder();
        while (decimalSource != 0) {
            sb.append(decimalSource % 2);
            decimalSource = decimalSource >> 1;
        }
        return sb.reverse().toString();
    }*/
    /**
     * @Description: 十进制转换成二进制
     * @param decimalSource
     * @return String
     */
    public static String decimalToBinaryJava(int decimalSource) {
        BigInteger bi = new BigInteger(String.valueOf(decimalSource)); // 转换成 BigInteger 类型，默认是十进制
        return bi.toString(2); // 参数 2 指定的是转化成二进制
    }

    /**
     * @Description: 二进制转换成十进制
     * @param binarySource
     * @return int
     */
    public static int binaryToDecimalJava(String binarySource) {
        BigInteger bi = new BigInteger(binarySource, 2);  // 转换为 BigInteger 类型，参数 2 指定的是二进制
        return Integer.parseInt(bi.toString());     // 默认转换成十进制
    }

    public static void main(String[] args) {
        System.out.println(RadixConversation.decimalToBinary(52));

        System.out.println(RadixConversation.decimalToBinaryNegative(-52));
        System.out.println(RadixConversation.decimalToBinaryJava(-52));
        System.out.println(RadixConversation.binaryToDecimal(110100));

    }
}
