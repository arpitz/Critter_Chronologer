package com.udacity.jdnd.course3.critter.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name="Critter_Customer")
public class Customer extends User{
    private String phoneNumber;
    private String notes;

    @Column
    @ElementCollection(targetClass = Long.class)
    private List<Long> petIds;
}
