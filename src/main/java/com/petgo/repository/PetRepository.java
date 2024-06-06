package com.petgo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petgo.entities.Pet;

public interface  PetRepository extends JpaRepository<Pet,Long>{

}
