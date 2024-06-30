package br.com.danielfreitassc.backend.dtos;

import br.com.danielfreitassc.backend.models.RelationsType;

public record RelationsDTO(
    Long id,
    RelationsType relations_type
) {
    
}
