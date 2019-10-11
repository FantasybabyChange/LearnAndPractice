package com.fantasybaby.algorithm.logicop;
/**
 * @author: liuxi
 * @time: 2019/10/10 16:04
 */
public class BitLogicObtain {
    static final int SHARED_SHIFT   = 16;
    static final int SHARED_UNIT    = (1 << SHARED_SHIFT);
    static final int MAX_COUNT      = (1 << SHARED_SHIFT) - 1;
    /**
     * 1111111111111111
     */
    static final int EXCLUSIVE_MASK = (1 << SHARED_SHIFT)-1;

    /** Returns the number of shared holds represented in count  */
    static int getHigherBit(int c)    { return c >>> SHARED_SHIFT; }
    /** Returns the number of exclusive holds represented in count  */
    static int getLowerBit(int c) { return c & EXCLUSIVE_MASK; }

    public static void main(String[] args) {
        int a = 1000000;
        String binary = RadixConversation.decimalToBinary(a);
        System.out.println(binary);
        int heighBit = getHigherBit(a);
        String heighBinary = RadixConversation.decimalToBinary(heighBit);
        System.out.println("b:"+heighBinary);
        int lowerBit = getLowerBit(a);
        String lowerBinary = RadixConversation.decimalToBinary(lowerBit);
        System.out.println("C:"+lowerBinary);

        System.out.println("C:"+EXCLUSIVE_MASK);
        String s1 = RadixConversation.decimalToBinary(EXCLUSIVE_MASK);
        System.out.println(s1);
    }

}
