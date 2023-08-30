package com.projectps.cinema.repository;

import com.projectps.cinema.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Integer> {
    List<Rating> findByScoreGreaterThanEqual(double score);
}
