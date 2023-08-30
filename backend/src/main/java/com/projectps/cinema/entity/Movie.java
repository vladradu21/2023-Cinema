package com.projectps.cinema.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "movie")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotEmpty(message = "Genres cannot be empty")
    @ElementCollection
    private Set<String> genres;

    @Min(value = 1900, message = "Year must be greater than 1900")
    @Max(value = 2023, message = "Year must be less than 2023")
    private int year;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", nullable = true)
    private List<Rating> ratings;

    @DecimalMin(value = "0.0", message = "Score must be greater than 0.0")
    @DecimalMax(value = "10.0", message = "Score must be less than 10.0")
    private double score;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "movie_actor",
            joinColumns = {
                    @JoinColumn(name = "movie_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "actor_id", referencedColumnName = "id")
            })
    private List<Actor> actors;

    @Min(value = 0, message = "Popularity must be greater than 0")
    private int popularity;
}
