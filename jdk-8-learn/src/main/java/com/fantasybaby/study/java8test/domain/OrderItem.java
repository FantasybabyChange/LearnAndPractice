package com.fantasybaby.study.java8test.domain;

import lombok.Data;

@Data
public class OrderItem {
    private Long productId;//商品ID
    private String productName;//商品名称
    private Double productPrice;//商品价格
    private Integer productQuantity;//商品数量
}
