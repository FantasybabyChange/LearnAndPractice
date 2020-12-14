package com.fantasybaby.study.java8test.lambadatest;

import org.hamcrest.CoreMatchers;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.summingInt;
import static org.hamcrest.MatcherAssert.assertThat;

public class MostPopularCollector<T> implements Collector<T, Map<T, Integer>, Optional<T>> {
    /**
     * 获取容器
     * @return
     */
    @Override
    public Supplier<Map<T, Integer>> supplier() {
        return HashMap::new;
    }

    @Override
    public BiConsumer<Map<T, Integer>, T> accumulator() {
        return (acc, elem) -> acc.merge(elem, 1, (old, value) -> old + value);
    }

    /**
     * 合并
     * @return
     */
    @Override
    public BinaryOperator<Map<T, Integer>> combiner() {
        return (a, b) -> Stream.concat(a.entrySet().stream(), b.entrySet().stream())
                .collect(Collectors.groupingBy(Map.Entry::getKey, summingInt(Map.Entry::getValue)));
    }

    /**
     * 结束
     * @return
     */
    @Override
    public Function<Map<T, Integer>, Optional<T>> finisher() {
        return (acc) -> acc.entrySet().stream()
                .reduce(BinaryOperator.maxBy(Map.Entry.comparingByValue()))
                .map(Map.Entry::getKey);
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.emptySet();
    }

    public static void main(String[] args) {
        assertThat(Stream.of(1, 1, 2, 2, 2, 3, 4, 5, 5).collect(new MostPopularCollector<>()).get(), CoreMatchers.is(2));
        assertThat(Stream.of('a', 'b', 'c', 'c', 'c', 'd').collect(new MostPopularCollector<>()).get(), CoreMatchers.is('c'));
    }
}