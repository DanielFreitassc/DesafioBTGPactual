package br.com.danielfreitassc.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.danielfreitassc.backend.dtos.PlaceDTO;
import br.com.danielfreitassc.backend.mappers.PlaceMapper;
import br.com.danielfreitassc.backend.models.PlaceEntity;
import br.com.danielfreitassc.backend.repositories.PlaceRepostiory;

@Service
public class PlaceService {
    @Autowired
    private PlaceRepostiory placeRepostiory;
    @Autowired
    private PlaceMapper placeMapper;


    public PlaceDTO createPlace(PlaceDTO placeDTO) {
        return placeMapper.toDto(placeRepostiory.save(placeMapper.toEntity(placeDTO)));
    }

    public List<PlaceDTO> getAllPlaces() {
        return placeRepostiory.findAll().stream().map(placeMapper::toDto).toList();
    }

    public PlaceDTO getById(Long id) {
        Optional<PlaceEntity> place = placeRepostiory.findById(id);
        if(place.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Nenhum lugar com este ID");
        return placeMapper.toDto(place.get());
    }


    public PlaceDTO updatePlaceById(Long id, PlaceDTO placeDTO) {
        Optional<PlaceEntity> place = placeRepostiory.findById(id);
        if(place.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Nenhum lugar com este ID");
        PlaceEntity placeEntity = placeMapper.toEntity(placeDTO);
        placeEntity.setId(id);
        return placeMapper.toDto(placeRepostiory.save(placeEntity));
    }

    public PlaceDTO deletePlace(Long id) {
        Optional<PlaceEntity> place = placeRepostiory.findById(id);
        if(place.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Nenhum lugar com este ID");
        placeRepostiory.delete(place.get());
        return placeMapper.toDto(place.get());
    }
}
