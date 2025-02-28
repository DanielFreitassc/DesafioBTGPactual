package com.danielfreitassc.orders.listener.dtos;

import java.math.BigDecimal;

public record OrderItemEvent(
    String produto,
    Integer quantidade,
    BigDecimal preco
) {
    
}
