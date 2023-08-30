package com.projectps.cinema.DTO;

import com.projectps.cinema.entity.Movie;
import com.projectps.cinema.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingDTO {
    private int id;
    private String title;
    private double score;
    private String description;
    private Movie movie;
    private User user;
}
