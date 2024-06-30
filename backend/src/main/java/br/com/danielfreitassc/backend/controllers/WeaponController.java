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

import br.com.danielfreitassc.backend.dtos.WeaponDTO;
import br.com.danielfreitassc.backend.services.WeaponService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("weapon")
public class WeaponController {
    @Autowired
    private WeaponService weaponService;

    @PostMapping
    public WeaponDTO createWeapon(@RequestBody @Valid WeaponDTO weaponDTO) {
        return weaponService.createWeapon(weaponDTO);
    }

    @GetMapping
    public List<WeaponDTO> getAllWeapons() {
        return weaponService.getAllWeapons();
    }

    @GetMapping("{id}")
    public WeaponDTO getById(@PathVariable Long id) {
        return weaponService.getById(id);
    }

    @PutMapping("{id}")
    public WeaponDTO updateWeapon(@PathVariable Long id, @RequestBody @Valid WeaponDTO weaponDTO) {
        return weaponService.updateWeapon(id, weaponDTO);
    }

    @DeleteMapping("{id}")
    public WeaponDTO deleteWeapon(@PathVariable Long id) {
        return weaponService.deleteWeapon(id);
    }
    
}
