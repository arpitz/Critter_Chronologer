package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.data.Employee;
import com.udacity.jdnd.course3.critter.data.Pet;
import com.udacity.jdnd.course3.critter.data.Schedule;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    PetService petService;

    @Autowired
    UserService userService;

    public ScheduleDTO saveSchedule(ScheduleDTO scheduleDTO){
        return convertScheduleToDTO(scheduleRepository.save(convertDTOToSchedule(scheduleDTO)));
    }

    public List<ScheduleDTO> findAllSchedules(){
        return convertToDTOList(scheduleRepository.findAll());
    }

    public List<ScheduleDTO> findScheduleByEmployee(Long id){
        Employee e = userService.getEmployeeById(id);
        List<Schedule> scheduleList = scheduleRepository.findScheduleByEmployees(e);
        return convertToDTOList(scheduleList);
    }

    public List<ScheduleDTO> findScheduleByPet(Long id){
        Pet pet = petService.findPetById(id);
        List<Schedule> scheduleList = scheduleRepository.findScheduleByPets(pet);
        return convertToDTOList(scheduleList);
    }

    public List<ScheduleDTO> findScheduleByCustomer(Long id){
        // should return all the schedules for the pets which this customer owns
        // find all pets owned by this customer
        // find all schedules for those pets
        List<Pet> petList = petService.getPetsByOwner(id);
        List<ScheduleDTO> scheduleDTOList = Lists.newArrayList();
        for(Pet p : petList){
            List<ScheduleDTO> scheduleDTOS = findScheduleByPet(p.getId());
            scheduleDTOList.addAll(scheduleDTOS);
        }
        return scheduleDTOList;
    }

    // Utility Methods
    private Schedule convertDTOToSchedule(ScheduleDTO scheduleDTO){
        Schedule schedule = new Schedule();
        List<Long> petIds = scheduleDTO.getPetIds();
        List<Pet> petList = new ArrayList<>();
        for(Long id: petIds){
            Pet pet = petService.findPetById(id);
            petList.add(pet);
        }
        schedule.setPets(petList);

        List<Long> employeeIds = scheduleDTO.getEmployeeIds();
        List<Employee> employeeList = new ArrayList<>();
        for(Long id: employeeIds){
            Employee employee = userService.getEmployeeById(id);
            employeeList.add(employee);
        }
        schedule.setEmployees(employeeList);

        schedule.setDate(scheduleDTO.getDate());
        schedule.setActivities(scheduleDTO.getActivities());
        return schedule;
    }

    public ScheduleDTO convertScheduleToDTO(Schedule schedule){
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setDate(schedule.getDate());
        scheduleDTO.setActivities(schedule.getActivities());

        List<Employee> employees = schedule.getEmployees();
        List<Long> empIds = new ArrayList<>();
        for(Employee employee: employees){
            empIds.add(employee.getId());
        }
        scheduleDTO.setEmployeeIds(empIds);

        List<Pet> pets = schedule.getPets();
        List<Long> petIds = new ArrayList<>();
        for(Pet pet : pets){
            petIds.add(pet.getId());
        }
        scheduleDTO.setPetIds(petIds);
        return scheduleDTO;
    }

    public List<ScheduleDTO> convertToDTOList(List<Schedule> scheduleList){
        List<ScheduleDTO> scheduleDTOList = Lists.newArrayList();
        for(Schedule schedule : scheduleList){
            ScheduleDTO scheduleDTO = convertScheduleToDTO(schedule);
            scheduleDTOList.add(scheduleDTO);
        }
        return scheduleDTOList;
    }
}
