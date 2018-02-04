package com.fantasybaby.hibernateValidtor.java8;

import com.fantasybaby.normaltest.java8test.change.IInterfaceTest;
import com.fantasybaby.normaltest.java8test.change.UseDefaultMethod;
import org.junit.Test;

public class TestJava8New {
    @Test
    public void testInterfaceAddMethod(){
        IInterfaceTest test = new UseDefaultMethod();
        test.workHard();
    }
}
