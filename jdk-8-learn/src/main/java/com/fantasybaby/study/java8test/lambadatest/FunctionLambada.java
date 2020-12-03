package com.fantasybaby.study.java8test.lambadatest;

import com.google.common.base.Predicate;
import com.google.common.base.Supplier;

import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;

public class FunctionLambada {
    /**
     * supplier接口
     * 返回数据
     */
    public void functionSupplier() {
        Supplier<String> returnSupplier = () -> "what";
        Supplier<String> returnSupplier1 = String::new;
        System.out.println(returnSupplier.get());
    }

    /**
     * 返回布尔
     */
    public void functionPredicate() {
        Predicate<Integer> tPredicate = i -> i > 0;
        Predicate<Integer> tPredicate1 = i -> i % 2 == 0;
        boolean test = tPredicate1.or(tPredicate1).test(2);
    }

    /**
     * Consumer接口是消费一个数据。我们通过andThen方法组合调用两个Consumer，输出两行abcdefg
     */
    public void functionConsumer() {
        Consumer println = System.out::println;
        println.andThen(println).accept("abcdefg");
    }

    /**
     * Function接口是输入一个数据，计算后输出一个数据。
     * 我们先把字符串转换为大写，然后通过andThen组合另一个Function实现字符串拼接
     */
    public void functionFunction() {
        Function<String, String> upperCase = String::toUpperCase;
        Function<String, String> duplicate = s -> s.concat(s);
        String test = upperCase.andThen(duplicate).apply("test");
        System.out.println(test);
    }

    /**
     * BinaryOperator是输入两个同类型参数，输出一个同类型参数的接口。
     * 这里我们通过方法引用获得一个整数加法操作，通过Lambda表达式定义一个减法操作，然后依次调用
     */
    public void functionBinaryOperator() {
        BinaryOperator<Integer> add = Integer::sum;
        BinaryOperator<Integer> subtraction = (a, b) -> a - b;
        Integer apply = subtraction.apply(add.apply(1, 2), 3);
        System.out.println(apply);

    }

    public static void main(String[] args) {
        new FunctionLambada().functionFunction();
        new FunctionLambada().functionBinaryOperator();

    }

}
