package com.udacity.jdnd.course3.critter.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "Critter_Schedule")
public class Schedule {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "schedule_emp")
    private Employee employee;

    @OneToMany(mappedBy = "schedule_pet")
    private Pet pet;
    private LocalDate date;
}
