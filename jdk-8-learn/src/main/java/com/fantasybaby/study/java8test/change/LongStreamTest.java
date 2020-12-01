package com.fantasybaby.study.java8test.change;

import java.util.stream.LongStream;

public class LongStreamTest {
    public static void main(String[] args) {
        LongStream longStream = LongStream.rangeClosed(1, 1000);
        LongStream range = LongStream.range(1, 1000);
        long[] longs = longStream.toArray();
        for (long aLong : longs) {
            System.out.println(aLong);
        }
        System.out.println("-----end ---");
        long[] longs1 = range.toArray();
        for (long l : longs1) {
            System.out.println(l);
        }
    }
}
