package com.petgo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petgo.entities.Pet;
import com.petgo.service.PetService;

@RestController
@RequestMapping("/Pet")
@CrossOrigin(origins = "*")
public class PetController {
    private final PetService PetService;

    @Autowired
    public PetController(PetService PetService) {
        this.PetService = PetService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> buscaPetControlId(@PathVariable long id) {
        Pet Pet = PetService.buscaPetPeloId(id);
        if (Pet != null) {
            return ResponseEntity.ok(Pet);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Pet>> buscaTodosPetsControl() {
        List<Pet> Pets = PetService.buscaTodosPets();
        return ResponseEntity.ok(Pets);
    }

    @PostMapping("/")
    public ResponseEntity<Pet> salvaPetControl(@RequestBody Pet Pet) {
        Pet salvaPet = PetService.salvaPet(Pet);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvaPet);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pet> alteraPetControl(@PathVariable Long id, @RequestBody Pet Pet) {
        Pet alteraPet = PetService.alterarPet(id, Pet);
        if (alteraPet != null) {
            return ResponseEntity.ok(Pet);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> apagaPetControl(@PathVariable Long id) {
        boolean apagar = PetService.apagarPet(id);
        if (apagar) {
        	 return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

