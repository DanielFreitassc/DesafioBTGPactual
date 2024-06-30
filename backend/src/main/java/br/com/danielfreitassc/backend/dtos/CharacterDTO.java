package br.com.danielfreitassc.backend.dtos;

import java.util.List;

import br.com.danielfreitassc.backend.models.GroupEntity;
import br.com.danielfreitassc.backend.models.PlaceEntity;
import br.com.danielfreitassc.backend.models.RelationsEntity;
import br.com.danielfreitassc.backend.models.WeaponEntity;

public record CharacterDTO(
    Long id,
    String name,
    String description,
    WeaponEntity weapon,
    int birthplace,
    String personality,
    List<GroupEntity> character_group,
    List<PlaceEntity> favorite_place,
    List<RelationsEntity> relation_id

) {
    
}
