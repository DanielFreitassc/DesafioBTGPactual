package br.com.danielfreitassc.backend.mappers;

import org.mapstruct.Mapper;

import br.com.danielfreitassc.backend.dtos.WeaponDTO;
import br.com.danielfreitassc.backend.models.WeaponEntity;

@Mapper(componentModel = "spring")
public interface WeaponMapper {

    WeaponDTO toDto(WeaponEntity weaponEntity);
    WeaponEntity toEntity(WeaponDTO weaponDTO);
    
}
