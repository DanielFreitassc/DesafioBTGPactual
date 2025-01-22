package com.danielfreitassc.orders.controllers.dtos;

import java.math.BigDecimal;

import com.danielfreitassc.orders.models.OrdersEntity;

public record OrderResponse(
    Long orderId,
    Long customerId,
    BigDecimal total

   
)   {

    public static OrderResponse fromEntity(OrdersEntity ordersEntity) {
        return new OrderResponse(ordersEntity.getOrderId(),ordersEntity.getCustomerId(),ordersEntity.getTotal());
    } 
}
