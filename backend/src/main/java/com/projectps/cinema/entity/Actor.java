package com.projectps.cinema.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "actor")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Actor {

    @Id
    @GeneratedValue
    private int id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @Min(value = 1, message = "Age must be greater than 1")
    private int age;

    @NotNull(message = "Gender is mandatory")
    private Gender gender;

    @NotBlank(message = "Origin country is mandatory")
    private String originCountry;
}
