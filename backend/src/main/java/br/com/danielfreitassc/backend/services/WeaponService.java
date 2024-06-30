package br.com.danielfreitassc.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.danielfreitassc.backend.dtos.WeaponDTO;
import br.com.danielfreitassc.backend.mappers.WeaponMapper;
import br.com.danielfreitassc.backend.models.WeaponEntity;
import br.com.danielfreitassc.backend.repositories.WeaponRepository;

@Service
public class WeaponService {
    @Autowired
    private WeaponRepository weaponRepository;
    @Autowired
    private WeaponMapper weaponMapper;

    public WeaponDTO createWeapon(WeaponDTO weaponDTO) {
        return weaponMapper.toDto(weaponRepository.save(weaponMapper.toEntity(weaponDTO)));
    }

    public List<WeaponDTO> getAllWeapons() {
        return weaponRepository.findAll().stream().map(weaponMapper::toDto).toList();
    }

    public WeaponDTO getById(Long id) {
        Optional<WeaponEntity> weapon = weaponRepository.findById(id);
        if(weapon.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Nenhuma arma com este ID");
        return weaponMapper.toDto(weapon.get());
    }

    public WeaponDTO updateWeapon(Long id, WeaponDTO weaponDTO) {
        Optional<WeaponEntity> weapon = weaponRepository.findById(id);
        if(weapon.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Nenhuma arma com este ID");
        WeaponEntity weaponEntity = weaponMapper.toEntity(weaponDTO);
        weaponEntity.setId(id);
        return weaponMapper.toDto(weaponRepository.save(weaponEntity));
    }

    public WeaponDTO deleteWeapon(Long id) {
        Optional<WeaponEntity> weapon = weaponRepository.findById(id);
        if(weapon.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Nenhuma arma com este ID");
        weaponRepository.delete(weapon.get());
        return weaponMapper.toDto(weapon.get());
    }
}
