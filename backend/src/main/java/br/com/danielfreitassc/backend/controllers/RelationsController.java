package br.com.danielfreitassc.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.danielfreitassc.backend.dtos.RelationsDTO;
import br.com.danielfreitassc.backend.services.RelationsService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("relations")
public class RelationsController {
    @Autowired
    private RelationsService relationsService;

    @PostMapping
    public RelationsDTO createRelation(@RequestBody @Valid RelationsDTO relationsDTO) {
        return relationsService.createRelation(relationsDTO);
    }

    @GetMapping
    public List<RelationsDTO> getAllRelations() {
        return relationsService.getAllRelations();
    }

    @GetMapping("{id}")
    public RelationsDTO getRelationById(@PathVariable Long id) {
        return relationsService.getRelationById(id);
    }

    @PutMapping("{id}")
    public RelationsDTO updateRelations(@PathVariable Long id, @RequestBody @Valid RelationsDTO relationsDTO) {
        return relationsService.updateRelationById(id, relationsDTO);
    }

    @DeleteMapping("{id}")
    public RelationsDTO deleteRelations(@PathVariable Long id) {
        return relationsService.deleteRelationById(id);
    }
}
