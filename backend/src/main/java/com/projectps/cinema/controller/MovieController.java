package com.projectps.cinema.controller;

import com.projectps.cinema.DTO.MovieDTO;
import com.projectps.cinema.entity.Movie;
import com.projectps.cinema.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
@CrossOrigin(origins = "http://localhost:4200")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("/addMovie")
    public Movie addMovie(@RequestBody MovieDTO movieDTO) {
        return movieService.saveMovie(movieDTO);
    }

    @PostMapping("/addMovies")
    public List<Movie> addMovies(@RequestBody List<MovieDTO> moviesDTO) {
        return movieService.saveMovies(moviesDTO);
    }

    @GetMapping("/allMovies")
    public List<MovieDTO> findAllMovies() {
        return movieService.getMovies();
    }

    @GetMapping("/byId/{id}")
    public MovieDTO findMovieById(@PathVariable int id) {
        return movieService.getMovieById(id);
    }

    @GetMapping("/byGenre/{genre}")
    public List<MovieDTO> findMoviesByGenre(@PathVariable String genre) {
        return movieService.getMoviesByGenre(genre);
    }

    @GetMapping("/byScore/{score}")
    public List<MovieDTO> findMoviesByScore(@PathVariable double score) {
        return movieService.getMoviesByScore(score);
    }

    @GetMapping("/byYear/{year}")
    public List<MovieDTO> findMoviesByYear(@PathVariable int year) {
        return movieService.getMoviesByYear(year);
    }

    @PutMapping("/updateMovie")
    public Movie updateMovie(@RequestBody MovieDTO movieDTO) {
        return movieService.updateMovie(movieDTO);
    }

    @DeleteMapping("/deleteMovie/{id}")
    public void deleteMovie(@PathVariable int id) {
        movieService.deleteMovie(id);
    }

    @GetMapping("/sortMoviesByTitle/{order}")
    public List<MovieDTO> sortMoviesByTitle(@PathVariable String order) {
        return movieService.sortMoviesByTitle(order);
    }

    @GetMapping("/sortMoviesByScore/{order}")
    public List<MovieDTO> sortMoviesByScore(@PathVariable String order) {
        return movieService.sortMoviesByScore(order);
    }

    @GetMapping("/sortMoviesByPopularity")
    public List<MovieDTO> sortMoviesByPopularity() {
        return movieService.getMoviesByPopularity();
    }
}
