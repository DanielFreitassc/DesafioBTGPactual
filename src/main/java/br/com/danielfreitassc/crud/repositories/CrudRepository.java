package br.com.danielfreitassc.crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.danielfreitassc.crud.models.CrudEntity;

@Repository
public interface CrudRepository extends JpaRepository<CrudEntity,Long>{

    Object findByName(String name);
    
}
