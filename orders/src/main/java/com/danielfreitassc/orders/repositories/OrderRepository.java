package com.danielfreitassc.orders.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.danielfreitassc.orders.models.OrdersEntity;

public interface OrderRepository extends MongoRepository<OrdersEntity, Long> {

    Page<OrdersEntity> findAllByCustomerId(Long customerId, PageRequest pageRequest);
    
}
