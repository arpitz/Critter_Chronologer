package com.udacity.jdnd.course3.critter.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="Critter_User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

//    For Customer
    @OneToMany(mappedBy = "user")
    private List<Pet> pets;

//    For Employee
    @OneToMany(mappedBy = "user")
    private List<Schedule> schedules;
}
