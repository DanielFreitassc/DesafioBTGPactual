package br.com.danielfreitassc.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.danielfreitassc.backend.dtos.CharacterDTO;
import br.com.danielfreitassc.backend.mappers.CharacterMapper;
import br.com.danielfreitassc.backend.models.CharacterEntity;
import br.com.danielfreitassc.backend.repositories.CharacterRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CharacterService {
    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private CharacterMapper characterMapper;

    public CharacterDTO createCharacter(CharacterDTO characterDTO) {
        return characterMapper.toDTO(characterRepository.save(characterMapper.toEntity(characterDTO)));
    }
    
    public List<CharacterDTO> getAllCharacters() {
        return characterRepository.findAll().stream().map(characterMapper::toDTO).toList();
    }

    public CharacterDTO getCharacterById(Long id) {
        Optional<CharacterEntity> character = characterRepository.findById(id);
        if(character.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Nenhum personagem com este ID");
        return characterMapper.toDTO(character.get());
    }

    public CharacterDTO updateCharacter(Long id, CharacterDTO characterDTO) {
        Optional<CharacterEntity> character = characterRepository.findById(id);
        if(character.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Nehum personagem com este ID");
        CharacterEntity characterEntity = characterMapper.toEntity(characterDTO);
        characterEntity.setId(id);
        CharacterEntity upadate = characterRepository.save(characterEntity);
        return characterMapper.toDTO(upadate);
    }

    public CharacterDTO deleteCharacter(Long id) {
        Optional<CharacterEntity> character = characterRepository.findById(id);
        if(character.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Nehum personagem com este ID");
        characterRepository.delete(character.get());
        return characterMapper.toDTO(character.get());
    }
}
