package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.data.Employee;
import com.udacity.jdnd.course3.critter.data.User;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Employee findEmployeeById(Long id);

    @Query("select u from User u where u.type='customer'")
    public List<User> findAllCustomers();

    @Query("select e from Employee e Left Join User u on e.id = u.id")
    public List<User> findAllEmployees();

    @Query("select u from User u where :id member of u.petIds")
    public User getOwnerByPet(Long id);

    @Query("select u from User u " +
            "where :skill member of u.skills AND " +
            ":day member of u.daysAvailable")
    public List<User> findEmployeesForService(EmployeeSkill skill, DayOfWeek day);
}
