package com.fantasybaby.study.java8test.lambadatest;

import com.fantasybaby.study.java8test.domain.Order;
import com.fantasybaby.study.java8test.domain.OrderItem;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static java.util.stream.Collectors.*;

public class CollectorTest {
    private List<Order> orders;
    private static Random random = new Random();

    public CollectorTest() {
        orders = Order.getData();
        orders.forEach(System.out::println);
        System.out.println("==========================================");
    }

    public void collectDemo() {
        //生成一定位数的随机字符串
        System.out.println(random.ints(48, 122)
                .filter(i -> (i < 57 || i > 65) && (i < 90 || i > 97))
                .mapToObj(i -> (char) i)
                .limit(20)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString());

        //所有下单的用户，使用toSet去重后实现字符串拼接
        System.out.println(orders.stream()
                .map(order -> order.getCustomerName()).collect(toSet())
                .stream().collect(joining(",", "[", "]")));

        //用toCollection收集器指定集合类型
        System.out.println(orders.stream().limit(2).collect(toCollection(LinkedList::new)).getClass());

        //使用toMap获取订单ID+下单用户名的Map
        orders.stream()
                .collect(toMap(Order::getId, Order::getCustomerName))
                .entrySet().forEach(System.out::println);

        //使用toMap获取下单用户名+最近一次下单时间的Map
        orders.stream()
                .collect(toMap(Order::getCustomerName, Order::getPlacedAt, (x, y) -> x.isAfter(y) ? x : y))
                .entrySet().forEach(System.out::println);

        //订单平均购买的商品数量
        System.out.println(orders.stream().collect(averagingInt(order ->
                order.getOrderItemList().stream()
                        .collect(summingInt(OrderItem::getProductQuantity)))));
    }
}
