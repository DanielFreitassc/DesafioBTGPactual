package br.com.danielfreitassc.crud.dtos;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CrudDto(@NotBlank String name, @NotNull BigDecimal price) {
    
}
