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

import br.com.danielfreitassc.backend.dtos.GroupDTO;
import br.com.danielfreitassc.backend.services.GroupService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("group")
public class GroupController {
    @Autowired
    private GroupService groupService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GroupDTO createGroup(@RequestBody @Valid GroupDTO groupDTO) {
        return groupService.createGroup(groupDTO);
    }

    @GetMapping
    public List<GroupDTO> getAllGroups() {
        return groupService.getAllGroups();
    }

    @GetMapping("{id}")
    public GroupDTO getById(@PathVariable Long id) {
        return groupService.getById(id);
    }

    @PutMapping("{id}")
    public GroupDTO updateGroup(@PathVariable Long id, @RequestBody @Valid GroupDTO groupDTO) {
        return groupService.updateById(id, groupDTO);
    }

    @DeleteMapping("{id}")
    public GroupDTO deleteGroup(@PathVariable Long id) {
        return groupService.deleteById(id);
    }
}
