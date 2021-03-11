package com.udacity.jdnd.course3.critter.data;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.DayOfWeek;
import java.util.Set;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name="Critter_Employee")
public class Employee extends User{
    @Column
    @ElementCollection
    private Set<EmployeeSkill> skills;

    @Column
    @ElementCollection
    private Set<DayOfWeek> daysAvailable;
}
