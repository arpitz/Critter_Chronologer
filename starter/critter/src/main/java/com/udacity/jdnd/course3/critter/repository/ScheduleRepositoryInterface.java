package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.data.Schedule;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepositoryInterface {
    Schedule persist(Schedule schedule);
    Schedule find(Long id);
    Schedule merge(Schedule schedule);
    void delete(Long id);
    List<Schedule> findAll();
}
