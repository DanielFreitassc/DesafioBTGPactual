package br.com.danielfreitassc.backend.dtos;

import br.com.danielfreitassc.backend.models.PlaceEntity;

public record CityDTO(
    Long id,
    String name,
    String description,
    PlaceEntity place
) {
    
}
