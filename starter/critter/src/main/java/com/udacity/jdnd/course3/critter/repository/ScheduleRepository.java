package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.data.Schedule;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class ScheduleRepository implements ScheduleRepositoryInterface{
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void persist(Schedule schedule) {
        entityManager.persist(schedule);
    }

    @Override
    public Schedule find(Long id) {
        return entityManager.find(Schedule.class, id);
    }

    @Override
    public Schedule merge(Schedule schedule) {
        return entityManager.merge(schedule);
    }

    @Override
    public void delete(Long id) {
        Schedule schedule = entityManager.find(Schedule.class, id);
        entityManager.remove(schedule);
    }
}
