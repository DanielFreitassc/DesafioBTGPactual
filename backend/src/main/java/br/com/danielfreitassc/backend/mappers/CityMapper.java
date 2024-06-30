package br.com.danielfreitassc.backend.mappers;

import org.mapstruct.Mapper;

import br.com.danielfreitassc.backend.dtos.CityDTO;
import br.com.danielfreitassc.backend.models.CityEntity;

@Mapper(componentModel = "spring")
public interface CityMapper {
    
    CityDTO toDto(CityEntity cityEntity);
    CityEntity toEntity(CityDTO cityDTO);
}
