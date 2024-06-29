package br.com.danielfreitassc.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.danielfreitassc.backend.models.GroupEntity;

public interface GroupRepository extends JpaRepository <GroupEntity, Long> {
    
}
