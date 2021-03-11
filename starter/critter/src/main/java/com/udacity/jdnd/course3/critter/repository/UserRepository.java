package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.data.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
