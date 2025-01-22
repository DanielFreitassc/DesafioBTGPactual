package com.danielfreitassc.orders.services;

import java.math.BigDecimal;

import org.bson.Document;
import org.bson.types.Decimal128;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import com.danielfreitassc.orders.controllers.dtos.OrderResponse;
import com.danielfreitassc.orders.listener.dtos.OrderCreatedEvent;
import com.danielfreitassc.orders.models.OrderItem;
import com.danielfreitassc.orders.models.OrdersEntity;
import com.danielfreitassc.orders.repositories.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MongoTemplate mongoTemplate; 

    public void save(OrderCreatedEvent orderCreatedEvent) {
        OrdersEntity ordersEntity = new OrdersEntity();
        ordersEntity.setOrderId(orderCreatedEvent.codigoPedido());
        ordersEntity.setCustomerId(orderCreatedEvent.codigoCliente());
        ordersEntity.setItems(orderCreatedEvent.itens().stream().map(i -> new OrderItem(i.produto(),i.quantidade(),i.preco())).toList());
        ordersEntity.setTotal(getTotal(orderCreatedEvent));

        orderRepository.save(ordersEntity);
    }

    public Page<OrderResponse> findAllByCustomerId(Long customerId,PageRequest pageRequest) {
        var ordersEntity = orderRepository.findAllByCustomerId(customerId, pageRequest);
        return ordersEntity.map(OrderResponse::fromEntity);
    }

    public BigDecimal findTotalOnOrdersByCustomerId(Long customerId) {
        var aggregation = Aggregation.newAggregation(
            Aggregation.match(Criteria.where("customerId").is(customerId)),
            Aggregation.group().sum("total").as("total")
        );

        var response = mongoTemplate.aggregate(aggregation, "tb_orders", Document.class);
        Document result = response.getUniqueMappedResult();
        if (result != null && result.containsKey("total")) {
            return result.get("total", Decimal128.class).bigDecimalValue();
        }
        return BigDecimal.ZERO;
    }


    private BigDecimal getTotal(OrderCreatedEvent orderCreatedEvent) {
        return orderCreatedEvent.itens().stream().map(i -> i.preco().multiply(BigDecimal.valueOf(i.quantidade()))).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
    }
}
