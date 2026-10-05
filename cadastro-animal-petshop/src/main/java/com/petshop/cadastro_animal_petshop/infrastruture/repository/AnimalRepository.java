package com.petshop.cadastro_animal_petshop.infrastruture.repository;

import com.petshop.cadastro_animal_petshop.infrastruture.entitys.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {

    List<Animal> findByNome(String nome);
}