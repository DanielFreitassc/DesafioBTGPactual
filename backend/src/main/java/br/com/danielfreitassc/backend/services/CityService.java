package br.com.danielfreitassc.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.danielfreitassc.backend.dtos.CityDTO;
import br.com.danielfreitassc.backend.mappers.CityMapper;
import br.com.danielfreitassc.backend.models.CityEntity;
import br.com.danielfreitassc.backend.repositories.CityRepository;

@Service
public class CityService {
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private CityMapper cityMapper;

    public CityDTO createCity(CityDTO cityDTO) {
        return cityMapper.toDto(cityRepository.save(cityMapper.toEntity(cityDTO)));
    }

    public List<CityDTO> getAllCities() {
        return cityRepository.findAll().stream().map(cityMapper::toDto).toList();
    }

    public CityDTO getCityById(Long id) {
        Optional<CityEntity> city = cityRepository.findById(id);
        if(city.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Nenhuma cidade com este ID");
        return cityMapper.toDto(city.get());
    }

    public CityDTO upadeCity(Long id, CityDTO cityDTO) {
        Optional<CityEntity> city = cityRepository.findById(id);
        if(city.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Nenhuma cidade com este ID");
        CityEntity cityEntity = cityMapper.toEntity(cityDTO);
        cityEntity.setId(id);
        return cityMapper.toDto(cityRepository.save(cityEntity));
    }

    public CityDTO deleteCity(Long id) {
        Optional<CityEntity> city = cityRepository.findById(id);
        if(city.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Nenhuma cidade com este ID");
        cityRepository.delete(city.get());
        return cityMapper.toDto(city.get());
    }

}
