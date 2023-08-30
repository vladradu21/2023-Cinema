package com.projectps.cinema.mapper;

import com.projectps.cinema.DTO.RatingDTO;
import com.projectps.cinema.entity.Rating;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RatingMapper {

    public static RatingDTO toRatingDTO(Rating rating) {
        return new RatingDTO(
                rating.getId(),
                rating.getTitle(),
                rating.getScore(),
                rating.getDescription(),
                rating.getMovie(),
                rating.getUser()
        );
    }

    public static Rating toRating(RatingDTO ratingDTO) {
        return new Rating(
                ratingDTO.getId(),
                ratingDTO.getTitle(),
                ratingDTO.getScore(),
                ratingDTO.getDescription(),
                ratingDTO.getMovie(),
                ratingDTO.getUser()
        );
    }

    public static List<RatingDTO> toRatingDTOList(List<Rating> ratings) {
        return ratings.stream()
                      .map(RatingMapper::toRatingDTO)
                      .toList();
    }

    public static List<Rating> toRatingList(List<RatingDTO> ratingsDTO) {
        return ratingsDTO.stream()
                         .map(RatingMapper::toRating)
                         .toList();
    }
}
