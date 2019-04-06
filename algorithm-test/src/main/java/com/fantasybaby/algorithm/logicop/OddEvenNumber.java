package com.fantasybaby.algorithm.logicop;
/**
 * 使用取模和按位与
 * @author: liuxi
 * @time: 2019/3/31 19:29
 */
public class OddEvenNumber {
    private int total = 100000000;
    private int oddCount = 0;
    private int eventCount = 0;
    public void useMod(){
        Long start = System.currentTimeMillis();
        for (int i=0;i<total;i++){
            if(i%2 > 0){
                oddCount++;
            }else{
                eventCount++;
            }
        }
        System.out.println(System.currentTimeMillis()-start);
        System.out.println(oddCount+":"+eventCount);
    }
    public void useBit(){
        Long start = System.currentTimeMillis();
        for (int i=0;i<total;i++){
            if((i&1)!=0){
                oddCount++;
            }else{
                eventCount++;
            }
        }
        System.out.println(System.currentTimeMillis()-start);
        System.out.println(oddCount+":"+eventCount);
    }

    public static void main(String[] args) {
        new OddEvenNumber().useBit();
        new OddEvenNumber().useMod();
    }
}
