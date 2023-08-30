package com.projectps.cinema.DTO;

import com.projectps.cinema.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActorDTO {
    private int id;
    private String name;
    private int age;
    private Gender gender;
    private String originCountry;
}
