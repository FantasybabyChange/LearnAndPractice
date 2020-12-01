package com.fantasybaby.study.java8test.change;

public interface IInterfaceTest {
    void workHard();
    default  String getName(String name){
        return "hello:" + name;
    }
}
