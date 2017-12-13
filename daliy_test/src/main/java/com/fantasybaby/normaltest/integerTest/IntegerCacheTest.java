package com.fantasybaby.normaltest.integerTest;

import org.junit.Test;

import java.lang.reflect.Field;

/**
 * Created by FantasyBaby on 2016-12-28.
 */
public class IntegerCacheTest {
    @Test
    public void testInteger(){
        Class cache = Integer.class.getDeclaredClasses()[0]; //1
        try {
            Field myCache = cache.getDeclaredField("cache"); //2
            myCache.setAccessible(true);//3
            Integer[] newCache = (Integer[]) myCache.get(cache); //4
            newCache[132] = newCache[133]; //5
            for (int i = 0; i < newCache.length; i++) {
                System.out.println(newCache[i]);
            }

            int a = 2;
            Integer b = a + a;
            System.out.println(b);
            System.out.printf("%d + %d = %d", a, a, b); //
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
