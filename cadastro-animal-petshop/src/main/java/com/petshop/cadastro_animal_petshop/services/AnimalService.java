package com.petshop.cadastro_animal_petshop.services;

import com.petshop.cadastro_animal_petshop.infrastruture.entitys.Animal;
import com.petshop.cadastro_animal_petshop.infrastruture.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(readOnly = true)
    public List<Animal> buscarPorNome(String nome) {
        return animalRepository.findByNome(nome);
    }

    @Transactional
    public Animal atualizarAnimal(int id, Animal animalAtualizado) {
        // 1. O Hibernate busca no banco e começa a "monitorar" este objeto (Managed State)
        Animal animalExistente = buscarPorId(id);

        // 2. Atualizamos apenas o que não for nulo direto no objeto monitorado
        if (animalAtualizado.getNome() != null) {
            animalExistente.setNome(animalAtualizado.getNome());
        }
        if (animalAtualizado.getEspecie() != null) {
            animalExistente.setEspecie(animalAtualizado.getEspecie());
        }
        if (animalAtualizado.getRaca() != null) {
            animalExistente.setRaca(animalAtualizado.getRaca());
        }
        if (animalAtualizado.getIdade() != null) {
            animalExistente.setIdade(animalAtualizado.getIdade());
        }
        if (animalAtualizado.getPeso() != null) {
            animalExistente.setPeso(animalAtualizado.getPeso());
        }
        if (animalAtualizado.getSexo() != null) {
            animalExistente.setSexo(animalAtualizado.getSexo());
        }

        // 3. Salvamos. Como o animalExistente já tem o ID original e já está conectado ao banco,
        // o JPA fará um UPDATE com certeza absoluta.
        return animalRepository.save(animalExistente);
    }

    @Transactional
    public void deletarAnimal(int id) {
        Animal animal = buscarPorId(id);
        animalRepository.delete(animal);
    }
}