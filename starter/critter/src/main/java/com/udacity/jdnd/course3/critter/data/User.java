package com.udacity.jdnd.course3.critter.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="Critter_User")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
}
