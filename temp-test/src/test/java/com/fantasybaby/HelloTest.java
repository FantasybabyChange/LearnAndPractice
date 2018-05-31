package com.fantasybaby;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author liuxi
 * @date2018年05月31日 15:48
 */
public class HelloTest {
    @Test
    public void test1(){
        int a = 1;
        Assert.assertEquals(1,a);
    }
    @Test
    public void test2(){
        String hello = Util.hello();
        Assert.assertEquals("2",hello);
    }
}
