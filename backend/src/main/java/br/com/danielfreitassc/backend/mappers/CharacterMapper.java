package br.com.danielfreitassc.backend.mappers;

import org.mapstruct.Mapper;

import br.com.danielfreitassc.backend.dtos.CharacterDTO;
import br.com.danielfreitassc.backend.models.CharacterEntity;

@Mapper(componentModel = "spring")
public interface CharacterMapper {

    CharacterDTO toDTO(CharacterEntity characterEntity);

    CharacterEntity toEntity(CharacterDTO characterDTO);
    
}
