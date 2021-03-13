package com.udacity.jdnd.course3.critter.data;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name="Critter_Employee")
public class Employee extends User{
    @ElementCollection
    private Set<EmployeeSkill> skills;

    private String type="employee";

    @ElementCollection
    private Set<DayOfWeek> daysAvailable;
}
