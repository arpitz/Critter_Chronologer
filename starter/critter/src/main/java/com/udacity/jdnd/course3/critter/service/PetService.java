package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.data.Pet;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    @Autowired
    PetRepository petRepository;

    public Pet findPetById(Long id){
        Optional<Pet> optionalPet = petRepository.findById(id);
        Pet pet = optionalPet.orElseThrow(PetNotFoundException::new);
        return pet;
    }

    public List<Pet> retrieveAllPets(){
        return (List<Pet>) petRepository.findAll();
    }

    public Pet savePet(Pet pet){
        return petRepository.save(pet);
    }

    public List<Pet> getPetsByOwner(Long ownerId) {
        List<Pet> petList = petRepository.getPetsByOwner(ownerId);
        return petList;
    }

    public void deletePet(Long id){
        petRepository.deleteById(id);
    }
}
