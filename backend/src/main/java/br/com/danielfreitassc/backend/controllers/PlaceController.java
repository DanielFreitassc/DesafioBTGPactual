package br.com.danielfreitassc.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.danielfreitassc.backend.dtos.PlaceDTO;
import br.com.danielfreitassc.backend.services.PlaceService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("place")
public class PlaceController {
    @Autowired
    private PlaceService placeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlaceDTO createPlace(@RequestBody @Valid PlaceDTO placeDTO) {
        return placeService.createPlace(placeDTO);
    }

    @GetMapping
    public List<PlaceDTO> getAllPlaces() {
        return placeService.getAllPlaces();
    }

    @GetMapping("{id}")
    public PlaceDTO getPlaceById(@PathVariable Long id) {
        return placeService.getById(id);
    }

    @PutMapping("{id}")
    public PlaceDTO updatePlace(@PathVariable Long id, @RequestBody @Valid PlaceDTO placeDTO) {
        return placeService.updatePlaceById(id, placeDTO);
    }

    @DeleteMapping("{id}")
    public PlaceDTO deletePlaceById(@PathVariable Long id) {
        return placeService.deletePlace(id);
    }
}
