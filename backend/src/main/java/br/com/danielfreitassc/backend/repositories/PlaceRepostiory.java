package br.com.danielfreitassc.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.danielfreitassc.backend.models.PlaceEntity;

public interface PlaceRepostiory extends JpaRepository<PlaceEntity, Long>{
    
}
