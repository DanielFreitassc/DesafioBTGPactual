package com.danielfreitassc.orders.controllers.dtos;

import java.util.List;
import java.util.Map;

public record ApiResponseDto<T> (
    Map<String, Object> summary,
    List<T> data,
    PaginationResponse pagination
) {
    
}
