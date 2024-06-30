package br.com.danielfreitassc.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.danielfreitassc.backend.models.CharacterEntity;

public interface CharacterRepository extends JpaRepository<CharacterEntity, Long> {
    
}
