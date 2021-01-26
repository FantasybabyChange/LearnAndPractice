package com.fantasybaby.dee.code.list;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Assert;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author fantasybaby
 * @date 2021/1/26
 **/
public class ChooseRight {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class Order {
        private int orderId;
    }

    private static Object listSearch(int elementCount, int loopCount) {
        List<Order> list = IntStream.rangeClosed(1, elementCount).mapToObj(i -> new Order(i)).collect(Collectors.toList());
        IntStream.rangeClosed(1, loopCount).forEach(i -> {
            int search = ThreadLocalRandom.current().nextInt(elementCount);
            Order result = list.stream().filter(order -> order.getOrderId() == search).findFirst().orElse(null);
            Assert.assertTrue(result != null && result.getOrderId() == search);

        });
        return list;
    }
}
