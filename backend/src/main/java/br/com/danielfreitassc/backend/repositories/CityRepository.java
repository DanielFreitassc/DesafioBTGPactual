package br.com.danielfreitassc.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.danielfreitassc.backend.models.CityEntity;

public interface CityRepository extends JpaRepository<CityEntity, Long>{
    
}
