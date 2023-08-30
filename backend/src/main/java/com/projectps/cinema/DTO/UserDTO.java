package com.projectps.cinema.DTO;

import com.projectps.cinema.entity.Movie;
import com.projectps.cinema.entity.Rating;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private int id;
    private boolean isAdmin;
    private String name;
    private String email;
    private String username;
    private String password;
    private List<Rating> ratings;
    private List<Movie> favoriteMovies;
    private List<Movie> watchList;
    private ZonedDateTime lastLogin;
}
