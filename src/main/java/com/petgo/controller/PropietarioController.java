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

import com.petgo.entities.Propietario;
import com.petgo.service.PropietarioService;

@RestController
@RequestMapping("/Propietario")
@CrossOrigin(origins = "*")
public class PropietarioController {
    private final PropietarioService PropietarioService;

    @Autowired
    public PropietarioController(PropietarioService PropietarioService) {
        this.PropietarioService = PropietarioService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Propietario> buscaPropietarioControlId(@PathVariable long id) {
        Propietario Propietario = PropietarioService.buscaPropietarioPeloId(id);
        if (Propietario != null) {
            return ResponseEntity.ok(Propietario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Propietario>> buscaTodosPropietariosControl() {
        List<Propietario> Propietarios = PropietarioService.buscaTodosPropietarios();
        return ResponseEntity.ok(Propietarios);
    }

    @PostMapping("/")
    public ResponseEntity<Propietario> salvaPropietarioControl(@RequestBody Propietario Propietario) {
        Propietario salvaPropietario = PropietarioService.salvaPropietario(Propietario);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvaPropietario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Propietario> alteraPropietarioControl(@PathVariable Long id, @RequestBody Propietario Propietario) {
        Propietario alteraPropietario = PropietarioService.alterarPropietario(id, Propietario);
        if (alteraPropietario != null) {
            return ResponseEntity.ok(Propietario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> apagaPropietarioControl(@PathVariable Long id) {
        boolean apagar = PropietarioService.apagarPropietario(id);
        if (apagar) {
        	 return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
