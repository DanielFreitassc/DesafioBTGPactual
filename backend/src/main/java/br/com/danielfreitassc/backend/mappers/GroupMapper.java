package br.com.danielfreitassc.backend.mappers;

import org.mapstruct.Mapper;

import br.com.danielfreitassc.backend.dtos.GroupDTO;
import br.com.danielfreitassc.backend.models.GroupEntity;

@Mapper(componentModel = "spring")
public interface GroupMapper {
    
    GroupDTO toDto(GroupEntity groupEntity);
    GroupEntity toEntity(GroupDTO groupDTO);
}
