package com.petshop.cadastro_animal_petshop.services;

import com.petshop.cadastro_animal_petshop.infrastruture.entitys.Animal;
import com.petshop.cadastro_animal_petshop.infrastruture.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Import correto

import java.util.List;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    @Transactional
    public Animal salvarAnimal(Animal animal) {
        return animalRepository.saveAndFlush(animal);
    }

    @Transactional(readOnly = true)
    public List<Animal> listarAnimais() {
        return animalRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Animal buscarPorId(int id) {
        return animalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal não encontrado com o id: " + id));
    }


    @Transactional
    public Animal atualizarAnimal(int id, Animal animal) {
        Animal animalExistente = buscarPorId(id);

        Animal animalAtualizado = Animal.builder()

                .nome(animal.getNome() != null ? animal.getNome() : animalExistente.getNome())
                .especie(animal.getEspecie() != null ? animal.getEspecie() : animalExistente.getEspecie())
                .raca(animal.getRaca() != null ? animal.getRaca() : animalExistente.getRaca())

                .idade(animal.getIdade() > 0 ? animal.getIdade() : animalExistente.getIdade())
                .peso(animal.getPeso() > 0.0 ? animal.getPeso() : animalExistente.getPeso())
                .sexo(animal.getSexo() != '\u0000' ? animal.getSexo() : animalExistente.getSexo())

                .build();

        return animalRepository.save(animalAtualizado);
    }

    @Transactional
    public void deletarAnimal(int id) {
        Animal animal = buscarPorId(id);
        animalRepository.delete(animal);
    }
}