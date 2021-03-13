package com.udacity.jdnd.course3.critter.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name="Critter_Customer")
public class Customer extends User{
    private String type="customer";
    private String phoneNumber;
    private String notes;

    @ElementCollection
    private List<Long> petIds;
}
