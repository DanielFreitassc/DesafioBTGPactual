package com.danielfreitassc.orders.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import com.danielfreitassc.orders.listener.dtos.OrderCreatedEvent;
import com.danielfreitassc.orders.services.OrderService;

import lombok.RequiredArgsConstructor;

import static com.danielfreitassc.orders.config.RabbitMqConfig.ORDER_CREATED_QUEUE;

@Component
@RequiredArgsConstructor
public class OrderCreatedListener {
    private final Logger logger = LoggerFactory.getLogger(OrderCreatedListener.class);
    private final OrderService orderService;

    @RabbitListener(queues = ORDER_CREATED_QUEUE)
    public void listen(Message<OrderCreatedEvent> message) {
        logger.info("Message consumed: {}", message);
        orderService.save(message.getPayload());
    }
}
