package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.data.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
