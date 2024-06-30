package br.com.danielfreitassc.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.danielfreitassc.backend.models.RelationsEntity;

public interface RelationsRepository extends JpaRepository<RelationsEntity, Long>{
    
}
