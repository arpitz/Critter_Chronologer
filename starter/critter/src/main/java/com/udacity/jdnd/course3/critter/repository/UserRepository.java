package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.data.User;
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
    @Query("select u from User u where u.type='customer'")
    public List<User> findAllCustomers();

    @Query("select e from Employee e Left Join User u on e.id = u.id")
    public List<User> findAllEmployees();

    @Query("select u from User u where :id member of u.petIds")
    public User getOwnerByPet(Long id);

//    @Transactional
//    @Modifying
//    @Query("update Employee e set e.daysAvailable = :daysAvailable where e.id=:id")
////    @Query(value = "update employee_days_available set " +
////            "days_available = :daysAvailable where employee_id = :id", nativeQuery = true)
//    public void setAvailabilityForEmployee(Set<DayOfWeek> daysAvailable, Long id);

//    @Query("update Employee e set e.daysAvailable = null where e.id=:id")
//    public void deleteAvailability(Long id);

}
