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

import com.petgo.entities.Veterinario;
import com.petgo.service.VeterinarioService;

@RestController
@RequestMapping("/Veterinario")
@CrossOrigin(origins = "*")
public class VeterinarioController {
    private final VeterinarioService VeterinarioService;

    @Autowired
    public VeterinarioController(VeterinarioService VeterinarioService) {
        this.VeterinarioService = VeterinarioService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veterinario> buscaVeterinarioControlId(@PathVariable long id) {
        Veterinario Veterinario = VeterinarioService.buscaVeterinarioPeloId(id);
        if (Veterinario != null) {
            return ResponseEntity.ok(Veterinario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Veterinario>> buscaTodosVeterinariosControl() {
        List<Veterinario> Veterinarios = VeterinarioService.buscaTodosVeterinarios();
        return ResponseEntity.ok(Veterinarios);
    }

    @PostMapping("/") ResponseEntity<Veterinario> salvaVeterinarioControl(@RequestBody Veterinario Veterinario) {
        Veterinario salvaVeterinario = VeterinarioService.salvaVeterinario(Veterinario);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvaVeterinario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Veterinario> alteraVeterinarioControl(@PathVariable Long id, @RequestBody Veterinario Veterinario) {
        Veterinario alteraVeterinario = VeterinarioService.alterarVeterinario(id, Veterinario);
        if (alteraVeterinario != null) {
            return ResponseEntity.ok(Veterinario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> apagaVeterinarioControl(@PathVariable Long id) {
        boolean apagar = VeterinarioService.apagarVeterinario(id);
        if (apagar) {
        	 return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
