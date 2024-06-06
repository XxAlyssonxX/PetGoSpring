package com.petgo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petgo.entities.Veterinario;

public interface  VeterinarioRepository extends JpaRepository<Veterinario,Long>{

}