package br.com.danielfreitassc.backend.dtos;

import br.com.danielfreitassc.backend.models.GroupEntity;
import br.com.danielfreitassc.backend.models.PlaceEntity;
import br.com.danielfreitassc.backend.models.WeaponEntity;

public record CharacterDTO(
    Long id,
    String name,
    String description,
    WeaponEntity weapon,
    int birthplace,
    String personality,
    GroupEntity group,
    PlaceEntity favorite_place

) {
    
}
