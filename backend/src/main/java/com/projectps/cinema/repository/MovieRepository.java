package com.projectps.cinema.repository;

import com.projectps.cinema.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MovieRepository extends JpaRepository<Movie, Integer> {

    List<Movie> findByGenresContaining(String genre);

    List<Movie> findByScoreGreaterThanEqual(double score);

    List<Movie> findByYearGreaterThanEqual(int year);

    List<Movie> findAllByOrderByTitleAsc();

    List<Movie> findAllByOrderByTitleDesc();

    List<Movie> findAllByOrderByScoreAsc();

    List<Movie> findAllByOrderByScoreDesc();

    List<Movie> findAllByOrderByPopularityDesc();
}
