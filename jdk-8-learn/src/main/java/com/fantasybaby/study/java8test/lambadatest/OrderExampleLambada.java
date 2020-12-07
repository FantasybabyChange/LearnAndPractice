package com.fantasybaby.study.java8test.lambadatest;

import com.fantasybaby.study.java8test.domain.Order;
import com.fantasybaby.study.java8test.domain.OrderItem;
import org.hamcrest.CoreMatchers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.atomic.LongAdder;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.joining;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author fanta
 * @Description
 * @create 2020-12-05 15:02
 */
public class OrderExampleLambada {

    private static Random random = new Random();
    private List<Order> orders;

    public OrderExampleLambada() {
        orders = Order.getData();
        orders.forEach(System.out::println);
        System.out.println("==========================================");
    }

    /**
     * 使用订单去过滤
     */
    public void orderFilter() {
        //最近半年的金额大于40的订单
        orders.stream()
                .filter(Objects::nonNull) //过滤null值
                .filter(order -> order.getPlacedAt().isAfter(LocalDateTime.now().minusMonths(6))) //最近半年的订单
                .filter(order -> order.getTotalPrice() > 40) //金额大于40的订单
                .forEach(System.out::println);
    }

    public void orderMap() {
        //计算所有订单商品数量
        //通过两次遍历实现
        LongAdder longAdder = new LongAdder();
        orders.stream().forEach(order ->
                order.getOrderItemList().forEach(orderItem -> longAdder.add(orderItem.getProductQuantity())));
        //使用两次mapToLong+sum方法实现
        assertThat(longAdder.longValue(), CoreMatchers.is(orders.stream().mapToLong(order ->
                order.getOrderItemList().stream()
                        .mapToLong(OrderItem::getProductQuantity).sum()).sum()));
    }

    public void orderFlatMap() {

        //直接展开订单商品进行价格统计
        System.out.println(orders.stream()
                .flatMap(order -> order.getOrderItemList().stream())
                .mapToDouble(item -> item.getProductQuantity() * item.getProductPrice()).sum());

        //另一种方式flatMap+mapToDouble=flatMapToDouble
        System.out.println(orders.stream()
                .flatMapToDouble(order ->
                        order.getOrderItemList()
                                .stream().mapToDouble(item -> item.getProductQuantity() * item.getProductPrice()))
                .sum());
    }

    public void orderSorted() {
        //大于50的订单,按照订单价格倒序前5
        orders.stream().filter(order -> order.getTotalPrice() > 50)
                .sorted(comparing(Order::getTotalPrice).reversed())
                .limit(5)
                .forEach(System.out::println);
    }

    public void orderDistinct() {

        //去重的下单用户
        System.out.println(orders.stream().map(order -> order.getCustomerName()).distinct().collect(joining(",")));

        //所有购买过的商品
        System.out.println(orders.stream()
                .flatMap(order -> order.getOrderItemList().stream())
                .map(OrderItem::getProductName)
                .distinct().collect(joining(",")));
    }

    public void orderSkip() {
        //按照下单时间排序，查询前2个订单的顾客姓名和下单时间
        orders.stream()
                .sorted(comparing(Order::getPlacedAt))
                .map(order -> order.getCustomerName() + "@" + order.getPlacedAt())
                .limit(2).forEach(System.out::println);
        //按照下单时间排序，查询第3和第4个订单的顾客姓名和下单时间
        orders.stream()
                .sorted(comparing(Order::getPlacedAt))
                .map(order -> order.getCustomerName() + "@" + order.getPlacedAt())
                .skip(2).limit(2).forEach(System.out::println);
    }

    public static void main(String[] args) {
        new OrderExampleLambada().orderMap();
    }
}
