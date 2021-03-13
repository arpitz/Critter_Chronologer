package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.data.User;
import com.udacity.jdnd.course3.critter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User findUserById(Long id){
        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.orElseThrow(UserNotFoundException::new);
        return user;
    }

    public List<User> retrieveAllUsers(){
        return (List<User>) userRepository.findAll();
    }

    public List<User> retrieveAllCustomers(){
        return (List<User>) userRepository.findAllCustomers();
    }

    public List<User> retrieveAllEmployees(){
        return (List<User>) userRepository.findAllEmployees();
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public User getOwnerByPet(Long petId) {
        return userRepository.getOwnerByPet(petId);
    }

//    public void setAvailabilityForEmployee(Set<DayOfWeek> daysAvailable, Long employeeId){
//        userRepository.deleteAvailability(employeeId);
//        userRepository.setAvailabilityForEmployee(daysAvailable, employeeId);
//    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

}
