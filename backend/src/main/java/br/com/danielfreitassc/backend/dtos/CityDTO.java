package br.com.danielfreitassc.backend.dtos;

import java.util.List;

import br.com.danielfreitassc.backend.models.PlaceEntity;

public record CityDTO(
    Long id,
    String name,
    String description,
    List<PlaceEntity> place
) {
    
}
