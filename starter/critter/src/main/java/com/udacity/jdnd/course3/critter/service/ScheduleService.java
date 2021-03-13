package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.data.Schedule;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;

    public Schedule saveSchedule(Schedule schedule){
        Schedule schedule1 = scheduleRepository.persist(schedule);
        return schedule1;
    }

    public List<Schedule> findAllSchedules(){
        return scheduleRepository.findAll();
    }

    public Schedule find(Long id){
        return scheduleRepository.find(id);
    }

    public Schedule update(Schedule schedule){
        return scheduleRepository.merge(schedule);
    }

    public void delete(Long id){
        scheduleRepository.delete(id);
    }
}
