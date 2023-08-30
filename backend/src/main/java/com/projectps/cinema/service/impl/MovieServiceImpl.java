package com.projectps.cinema.service.impl;

import com.projectps.cinema.DTO.MovieDTO;
import com.projectps.cinema.entity.Movie;
import com.projectps.cinema.entity.Rating;
import com.projectps.cinema.mapper.MovieMapper;
import com.projectps.cinema.repository.MovieRepository;
import com.projectps.cinema.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieMapper movieMapper;

    public static double calculateScore(MovieDTO movieDTO) {
        double score = 0;
        for(Rating rating : movieDTO.getRatings()) {
            score += rating.getScore();
        }
        return score / movieDTO.getRatings().size();
    }

    @Override
    public Movie saveMovie(MovieDTO movieDTO) {
        movieDTO.setScore(calculateScore(movieDTO));
        return movieRepository.save(movieMapper.toMovie(movieDTO));
    }

    @Override
    public List<Movie> saveMovies(List<MovieDTO> moviesDTO) {
        return movieRepository.saveAll(movieMapper.toMovieList(moviesDTO));
    }

    @Override
    public List<MovieDTO> getMovies() {
        List<Movie> movies = movieRepository.findAll();
        return movieMapper.toMovieDTOList(movies);
    }

    @Override
    public MovieDTO getMovieById(int id) {
        Movie movie = movieRepository.findById(id).orElse(null);
        return movieMapper.toMovieDTO(movie);
    }

    @Override
    public List<MovieDTO> getMoviesByGenre(String genre) {
        List<Movie> movies = movieRepository.findByGenresContaining(genre);
        return movieMapper.toMovieDTOList(movies);
    }

    @Override
    public List<MovieDTO> getMoviesByScore(double score) {
        List<Movie> movies = movieRepository.findByScoreGreaterThanEqual(score);
        return movieMapper.toMovieDTOList(movies);
    }

    @Override
    public List<MovieDTO> getMoviesByYear(int year) {
        List<Movie> movies = movieRepository.findByYearGreaterThanEqual(year);
        return movieMapper.toMovieDTOList(movies);
    }

    @Override
    public Movie updateMovie(MovieDTO movieDTO) {
        Movie existingMovie = movieRepository.findById(movieDTO.getId()).orElse(null);
        existingMovie.setTitle(movieDTO.getTitle());
        existingMovie.setGenres(movieDTO.getGenres());
        existingMovie.setYear(movieDTO.getYear());
        existingMovie.setRatings(movieDTO.getRatings());
        existingMovie.setScore(calculateScore(movieDTO));
        existingMovie.setActors(movieDTO.getActors());

        return movieRepository.save(existingMovie);
    }

    @Override
    public void deleteMovie(int id) {
        movieRepository.deleteById(id);
    }

    @Override
    public List<MovieDTO> sortMoviesByTitle(String order) {
        List<Movie> movies = movieRepository.findAllByOrderByTitleAsc();
        if (order.equals("desc")) {
            movies = movieRepository.findAllByOrderByTitleDesc();
        }
        return movieMapper.toMovieDTOList(movies);
    }

    @Override
    public List<MovieDTO> sortMoviesByScore(String order) {
        List<Movie> movies = movieRepository.findAllByOrderByScoreAsc();
        if (order.equals("desc")) {
            movies = movieRepository.findAllByOrderByScoreDesc();
        }
        return movieMapper.toMovieDTOList(movies);
    }

    @Override
    public MovieDTO incresePopularity(int id) {
        Movie movie = movieRepository.findById(id).orElse(null);
        movie.setPopularity(movie.getPopularity() + 1);
        return movieMapper.toMovieDTO(movieRepository.save(movie));
    }

    @Override
    public MovieDTO decreasePopularity(int id) {
        Movie movie = movieRepository.findById(id).orElse(null);
        movie.setPopularity(movie.getPopularity() - 1);
        return movieMapper.toMovieDTO(movieRepository.save(movie));
    }

    @Override
    public List<MovieDTO> getMoviesByPopularity() {
        List<Movie> movies = movieRepository.findAllByOrderByPopularityDesc();
        return movieMapper.toMovieDTOList(movies);
    }
}
