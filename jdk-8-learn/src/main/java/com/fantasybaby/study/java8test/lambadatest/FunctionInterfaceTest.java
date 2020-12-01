package com.fantasybaby.study.java8test.lambadatest;

/**
 * @author liuxi
 * @date2018年02月07日 15:36
 */
public class FunctionInterfaceTest {
    public static void main(String[] args) {
        IConvertType<String,Integer> convertType = (a) -> Integer.parseInt(a);
        Integer convert = convertType.convert("123");
        System.out.println(convert);
    }
}
