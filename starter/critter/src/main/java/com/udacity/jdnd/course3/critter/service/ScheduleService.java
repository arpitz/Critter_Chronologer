package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.data.Schedule;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;

    public void saveSchedule(Schedule schedule){
        scheduleRepository.persist(schedule);
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
