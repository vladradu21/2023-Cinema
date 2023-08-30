package com.projectps.cinema.service;

import com.projectps.cinema.DTO.MovieDTO;
import com.projectps.cinema.entity.Movie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovieService {

    Movie saveMovie(MovieDTO movieDTO);

    List<Movie> saveMovies(List<MovieDTO> moviesDTO);

    List<MovieDTO> getMovies();

    MovieDTO getMovieById(int id);

    List<MovieDTO> getMoviesByGenre(String genre);

    List<MovieDTO> getMoviesByScore(double score);

    List<MovieDTO> getMoviesByYear(int year);

    Movie updateMovie(MovieDTO movieDTO);

    void deleteMovie(int id);

    List<MovieDTO> sortMoviesByTitle(String order);

    List<MovieDTO> sortMoviesByScore(String order);

    MovieDTO incresePopularity(int id);

    MovieDTO decreasePopularity(int id);

    List<MovieDTO> getMoviesByPopularity();
}
