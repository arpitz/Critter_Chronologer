package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.data.Employee;
import com.udacity.jdnd.course3.critter.data.User;
import com.udacity.jdnd.course3.critter.repository.UserRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Employee getEmployeeById(Long employeeId){
        return userRepository.findEmployeeById(employeeId);
    }

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

    public void setAvailabilityForEmployee(Set<DayOfWeek> daysAvailable, Long employeeId){
        //  find that employee by his id
        Employee employee = (Employee) findUserById(employeeId);
        // set his daysAvailable in java
        employee.setDaysAvailable(daysAvailable);
        // save that employee
        userRepository.save(employee);
    }

    public List<User> findEmployeesForService(EmployeeRequestDTO employeeRequestDTO){
        Set<EmployeeSkill> skills = employeeRequestDTO.getSkills();
        LocalDate date = employeeRequestDTO.getDate();
        DayOfWeek day = date.getDayOfWeek();
        List<User> employees = new ArrayList<>();
        for(EmployeeSkill skill: skills){
             employees.addAll(userRepository.findEmployeesForService(skill, day));
        }

        if(skills.size() == 1){
            return employees;
        } else {
            List<User> selected = new ArrayList<>();
            for (int i = 0; i < employees.size(); i++) {
                for (int j = i+1; j < employees.size(); j++) {
                    if(employees.get(i).getId() == employees.get(j).getId()){
                        selected.add(employees.get(i));
                    }
                }
            }
            return selected;
        }
    }

}
