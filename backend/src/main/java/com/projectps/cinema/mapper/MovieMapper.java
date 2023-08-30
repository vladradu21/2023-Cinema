package com.projectps.cinema.mapper;

import com.projectps.cinema.DTO.MovieDTO;
import com.projectps.cinema.entity.Movie;
import com.projectps.cinema.entity.Rating;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MovieMapper {

    public static double calculateScore(Movie movie) {
        double score = 0;
        for(Rating rating : movie.getRatings()) {
            score += rating.getScore();
        }
        return score / movie.getRatings().size();
    }

    public static double calculateScore(MovieDTO movieDTO) {
        double score = 0;
        for(Rating rating : movieDTO.getRatings()) {
            score += rating.getScore();
        }
        return score / movieDTO.getRatings().size();
    }

    public static MovieDTO toMovieDTO(Movie movie) {
        return new MovieDTO(
                movie.getId(),
                movie.getTitle(),
                movie.getGenres(),
                movie.getYear(),
                movie.getRatings(),
                //calculateScore(movie),
                movie.getScore(),
                movie.getActors(),
                movie.getPopularity()
        );
    }

    public static Movie toMovie(MovieDTO movieDTO) {
        return new Movie(
                movieDTO.getId(),
                movieDTO.getTitle(),
                movieDTO.getGenres(),
                movieDTO.getYear(),
                movieDTO.getRatings(),
                //calculateScore(movieDTO),
                movieDTO.getScore(),
                movieDTO.getActors(),
                movieDTO.getPopularity()
        );
    }

    public static List<MovieDTO> toMovieDTOList(List<Movie> movies) {
        return movies.stream()
                     .map(MovieMapper::toMovieDTO)
                     .toList();
    }

    public static List<Movie> toMovieList(List<MovieDTO> moviesDTO) {
        return moviesDTO.stream()
                        .map(MovieMapper::toMovie)
                        .toList();
    }
}
