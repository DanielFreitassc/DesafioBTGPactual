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

import br.com.danielfreitassc.backend.dtos.CityDTO;
import br.com.danielfreitassc.backend.services.CityService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("city")
public class CityControlller {

    @Autowired
    private CityService cityService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CityDTO createCity(@RequestBody @Valid CityDTO cityDTO) {
        return cityService.createCity(cityDTO);
    }

    @GetMapping
    public List<CityDTO> getAllCities() {
        return cityService.getAllCities();
    }

    @GetMapping("{id}")
    public CityDTO getById(@PathVariable Long id) {
        return cityService.getCityById(id);
    }

    @PutMapping("{id}")
    public CityDTO updateCity(@PathVariable Long id, @RequestBody @Valid CityDTO cityDTO) {
        return cityService.upadeCity(id, cityDTO);
    }

    @DeleteMapping("{id}")
    public CityDTO deleteCity(@PathVariable Long id) {
        return cityService.deleteCity(id);
    }
    
}
