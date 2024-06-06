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

import com.petgo.entities.Consulta;
import com.petgo.service.ConsultaService;



@RestController
@RequestMapping("/Consulta")
@CrossOrigin(origins = "*")
public class ConsultaController {
    private final ConsultaService ConsultaService;

    @Autowired
    public ConsultaController(ConsultaService ConsultaService) {
        this.ConsultaService = ConsultaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consulta> buscaConsultaControlId(@PathVariable long id) {
        Consulta Consulta = ConsultaService.buscaConsultaPeloId(id);
        if (Consulta != null) {
            return ResponseEntity.ok(Consulta);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Consulta>> buscaTodosConsultasControl() {
        List<Consulta> Consultas = ConsultaService.buscaTodosConsultas();
        return ResponseEntity.ok(Consultas);
    }

    @PostMapping("/")
    public ResponseEntity<Consulta> salvaConsultaControl(@RequestBody Consulta Consulta) {
        Consulta salvaConsulta = ConsultaService.salvaConsulta(Consulta);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvaConsulta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Consulta> alteraConsultaControl(@PathVariable Long id, @RequestBody Consulta Consulta) {
        Consulta alteraConsulta = ConsultaService.alterarConsulta(id, Consulta);
        if (alteraConsulta != null) {
            return ResponseEntity.ok(Consulta);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> apagaConsultaControl(@PathVariable Long id) {
        boolean apagar = ConsultaService.apagarConsulta(id);
        if (apagar) {
        	 return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
