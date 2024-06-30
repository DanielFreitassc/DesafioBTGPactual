package br.com.danielfreitassc.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.danielfreitassc.backend.dtos.GroupDTO;
import br.com.danielfreitassc.backend.mappers.GroupMapper;
import br.com.danielfreitassc.backend.models.GroupEntity;
import br.com.danielfreitassc.backend.repositories.GroupRepository;

@Service
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private GroupMapper groupMapper;

    public GroupDTO createGroup(GroupDTO groupDTO) {
        return groupMapper.toDto(groupRepository.save(groupMapper.toEntity(groupDTO)));
    }

    public List<GroupDTO> getAllGroups() {
        return groupRepository.findAll().stream().map(groupMapper::toDto).toList();
    }

    public GroupDTO getById(Long id) {
        Optional<GroupEntity> group = groupRepository.findById(id);
        if(group.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum grupo com este ID");
        return groupMapper.toDto(group.get());
    }

    public GroupDTO updateById(Long id, GroupDTO groupDTO) {
        Optional<GroupEntity> group = groupRepository.findById(id);
        if(group.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum grupo com este ID");
        GroupEntity groupEntity = groupMapper.toEntity(groupDTO);
        groupEntity.setId(id);
        return groupMapper.toDto(groupRepository.save(groupEntity));
    }

    public GroupDTO deleteById(Long id) {
        Optional<GroupEntity> group = groupRepository.findById(id);
        if(group.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum grupo com este ID");
        groupRepository.delete(group.get());
        return groupMapper.toDto(group.get());
    }
   
}
