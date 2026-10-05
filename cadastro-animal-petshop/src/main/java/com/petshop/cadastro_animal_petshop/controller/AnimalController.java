package com.petshop.cadastro_animal_petshop.controller;

import com.petshop.cadastro_animal_petshop.infrastruture.entitys.Animal;
import com.petshop.cadastro_animal_petshop.services.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/animais")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @PostMapping
    public ResponseEntity<Animal> salvarAnimal(@RequestBody Animal animal) {
        Animal animalSalvo = animalService.salvarAnimal(animal);
        return ResponseEntity.status(HttpStatus.CREATED).body(animalSalvo);
    }

    @GetMapping
    public ResponseEntity<List<Animal>> listarAnimais() {
        List<Animal> animais = animalService.listarAnimais();
        return ResponseEntity.status(HttpStatus.OK).body(animais);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Animal> buscarAnimalPorId(@PathVariable int id) {
        try {
            Animal animal = animalService.buscarPorId(id);
            return ResponseEntity.status(HttpStatus.OK).body(animal);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/busca")
    public ResponseEntity<List<Animal>> buscarAnimalPorNome(@RequestParam String nome) {
        try {
            List<Animal> animais = animalService.buscarPorNome(nome);

            if (animais.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.status(HttpStatus.OK).body(animais);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Animal> atualizarAnimal(@PathVariable int id, @RequestBody Animal animalAtualizado) {
        try {
            Animal animal = animalService.atualizarAnimal(id, animalAtualizado);
            return ResponseEntity.status(HttpStatus.OK).body(animal);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAnimal(@PathVariable int id) {
        try {
            animalService.deletarAnimal(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}