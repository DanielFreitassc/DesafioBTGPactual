package br.com.danielfreitassc.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.danielfreitassc.backend.models.WeaponEntity;

public interface WeaponRepository extends JpaRepository<WeaponEntity, Long>{
    
}
