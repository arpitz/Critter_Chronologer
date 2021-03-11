package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.data.Schedule;

public interface ScheduleRepositoryInterface {
    void persist(Schedule schedule);
    Schedule find(Long id);
    Schedule merge(Schedule schedule);
    void delete(Long id);
}
