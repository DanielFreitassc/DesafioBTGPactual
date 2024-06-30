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

import br.com.danielfreitassc.backend.dtos.CharacterDTO;
import br.com.danielfreitassc.backend.services.CharacterService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("character")
public class CharacterController {
    @Autowired
    private CharacterService characterService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CharacterDTO createCharacter(@RequestBody @Valid CharacterDTO characterDTO) {
        return characterService.createCharacter(characterDTO);
    }
    
    @GetMapping
    public List<CharacterDTO> getAllCharacters() {
        return characterService.getAllCharacters();
    }

    @GetMapping("{id}")
    public CharacterDTO getCharacter(@PathVariable Long id) {
        return characterService.getCharacterById(id);
    }

    @PutMapping("{id}")
    public CharacterDTO updateCharacter(@PathVariable Long id, @RequestBody @Valid CharacterDTO characterDTO) {
        return characterService.updateCharacter(id, characterDTO);
    }
    
    @DeleteMapping("{id}")
    public CharacterDTO deleteCharacter(@PathVariable Long id) {
        return characterService.deleteCharacter(id);
    }
}
