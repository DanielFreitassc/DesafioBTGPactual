package com.danielfreitassc.orders.controllers.dtos;

import org.springframework.data.domain.Page;

public record PaginationResponse(
    Integer page,
    Integer pageSize,
    Long totalElements,
    Long totalPages
) {

    public static PaginationResponse fromPage(Page<?> page) {
        return new PaginationResponse(
            page.getNumber(), 
            page.getSize(), 
            page.getTotalElements(), 
            page.getTotalElements()
        );
    } 
}
