package com.danielfreitassc.orders.controllers;

import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.danielfreitassc.orders.controllers.dtos.ApiResponseDto;
import com.danielfreitassc.orders.controllers.dtos.OrderResponse;
import com.danielfreitassc.orders.controllers.dtos.PaginationResponse;
import com.danielfreitassc.orders.services.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class OrderController {
    private final OrderService orderService;


    @GetMapping("/customers/{customerId}/orders")
    public ResponseEntity<ApiResponseDto<OrderResponse>> listOrders(@PathVariable(name = "customerId") Long customerId, 
                                                                    @RequestParam(name = "page",defaultValue = "0") Integer page, 
                                                                    @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {

        var pageResponse = orderService.findAllByCustomerId(customerId, PageRequest.of(page, pageSize));
        var totalOnOrders = orderService.findTotalOnOrdersByCustomerId(customerId);
        return ResponseEntity.ok(new ApiResponseDto<>(Map.of("totalOnOrders",totalOnOrders) ,pageResponse.getContent(), PaginationResponse.fromPage(pageResponse)));
    }
}
