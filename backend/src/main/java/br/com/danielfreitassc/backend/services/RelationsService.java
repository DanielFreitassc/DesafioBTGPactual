package br.com.danielfreitassc.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.danielfreitassc.backend.dtos.RelationsDTO;
import br.com.danielfreitassc.backend.mappers.RelationsMapper;
import br.com.danielfreitassc.backend.models.RelationsEntity;
import br.com.danielfreitassc.backend.repositories.RelationsRepository;

@Service
public class RelationsService {
    @Autowired
    private RelationsRepository relationsRepository;
    @Autowired
    private RelationsMapper relationsMapper;

    public RelationsDTO createRelation(RelationsDTO relationsDTO) {
        return relationsMapper.toDTO(relationsRepository.save(relationsMapper.toEntity(relationsDTO)));
    }

    public List<RelationsDTO> getAllRelations() {
        return relationsRepository.findAll().stream().map(relationsMapper::toDTO).toList();
    }

    public RelationsDTO getRelationById(Long id) {
        Optional<RelationsEntity> relation = relationsRepository.findById(id);
        if(relation.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Nenhuma relação com este ID");
        return relationsMapper.toDTO(relation.get());
    }

    public RelationsDTO updateRelationById(Long id, RelationsDTO relationsDTO) {
        Optional<RelationsEntity> relation = relationsRepository.findById(id);
        if(relation.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Nenhuma relação com este ID");
        RelationsEntity relationsEntity = relationsMapper.toEntity(relationsDTO);
        relationsEntity.setId(id);
        return relationsMapper.toDTO(relationsRepository.save(relationsEntity));
    }

    public RelationsDTO deleteRelationById(Long id) {
        Optional<RelationsEntity> relation = relationsRepository.findById(id);
        if(relation.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Nenhuma relação com este ID");
        relationsRepository.delete(relation.get());
        return relationsMapper.toDTO(relation.get());
    }

}
