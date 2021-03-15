package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.data.Customer;
import com.udacity.jdnd.course3.critter.data.Pet;
import com.udacity.jdnd.course3.critter.data.User;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    @Autowired
    PetRepository petRepository;

    @Autowired
    UserService userService;

    public List<Pet> getAllPetsByIds(List<Long> ids){
        return (List<Pet>) petRepository.findAllById(ids);
    }

    public Pet findPetById(Long id){
        Optional<Pet> optionalPet = petRepository.findById(id);
        Pet pet = optionalPet.orElseThrow(PetNotFoundException::new);
        return pet;
    }

    public List<Pet> retrieveAllPets(){
        return (List<Pet>) petRepository.findAll();
    }

    public Pet savePet(Pet pet){
        Pet savedPet = petRepository.save(pet);

        // save the pet for that customer of ownerId
        Customer customer = (Customer) userService.findUserById(pet.getOwnerId());
        List<Long> petIdList;
        if(customer.getPetIds() != null && customer.getPetIds().size() > 0) {
            petIdList = new ArrayList<>(customer.getPetIds());
        } else {
            petIdList = new ArrayList<>();
        }
        petIdList.add(savedPet.getId());
        customer.setPetIds(petIdList);
        userService.saveUser(customer);

        return savedPet;

    }

    public List<Pet> getPetsByOwner(Long ownerId) {
        List<Pet> petList = petRepository.getPetsByOwner(ownerId);
        return petList;
    }

    public void deletePet(Long id){
        petRepository.deleteById(id);
    }
}
