package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.data.Pet;
import com.udacity.jdnd.course3.critter.data.User;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {
    @Autowired
    PetService petService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO){
        Pet pet = petService.savePet(convertFromPetDTO(petDTO));
        return convertToPetDTO(pet);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable Long petId) {
        Pet pet = petService.findPetById(petId);
        return convertToPetDTO(pet);
    }

    @GetMapping
    public List<PetDTO> getPets(){
        List<Pet> petList = petService.retrieveAllPets();
        return convertToPetDTOList(petList);
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable Long ownerId) {
        List<Pet> petList = petService.getPetsByOwner(ownerId);
        return convertToPetDTOList(petList);
    }

    private Pet convertFromPetDTO(PetDTO petDTO) {
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet);
        return pet;
    }

    private PetDTO convertToPetDTO(Pet pet) {
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet, petDTO);
        return petDTO;
    }

    private List<PetDTO> convertToPetDTOList(List<Pet> petList) {
        List<PetDTO> petDTOList = new ArrayList<>();
        for (Pet pet: petList) {
            PetDTO cd = new PetDTO();
            BeanUtils.copyProperties(pet, cd);
            petDTOList.add(cd);
        }
        return petDTOList;
    }
}
