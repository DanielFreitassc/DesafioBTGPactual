package br.com.danielfreitassc.backend.mappers;

import org.mapstruct.Mapper;

import br.com.danielfreitassc.backend.dtos.PlaceDTO;
import br.com.danielfreitassc.backend.models.PlaceEntity;

@Mapper(componentModel = "spring")
public interface PlaceMapper {
    
    PlaceDTO toDto(PlaceEntity placeEntity);
    PlaceEntity toEntity(PlaceDTO placeDTO);
}
