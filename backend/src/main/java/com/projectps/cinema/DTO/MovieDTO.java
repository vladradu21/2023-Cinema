package com.projectps.cinema.DTO;

import com.projectps.cinema.entity.Actor;
import com.projectps.cinema.entity.Rating;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {
    private int id;
    private String title;
    private Set<String> genres;
    private int year;
    private List<Rating> ratings;
    private double score;
    private List<Actor> actors;
    private int popularity;
}
