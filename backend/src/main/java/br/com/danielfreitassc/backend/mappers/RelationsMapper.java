package br.com.danielfreitassc.backend.mappers;

import org.mapstruct.Mapper;

import br.com.danielfreitassc.backend.dtos.RelationsDTO;
import br.com.danielfreitassc.backend.models.RelationsEntity;

@Mapper(componentModel = "spring")
public interface RelationsMapper {
    RelationsDTO toDTO(RelationsEntity relationsEntity);
    
    RelationsEntity toEntity(RelationsDTO relationsDTO);
    
}
