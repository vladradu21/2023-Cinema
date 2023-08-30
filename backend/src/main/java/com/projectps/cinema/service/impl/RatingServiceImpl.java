package com.projectps.cinema.service.impl;

import com.projectps.cinema.DTO.RatingDTO;
import com.projectps.cinema.entity.Movie;
import com.projectps.cinema.entity.Rating;
import com.projectps.cinema.entity.User;
import com.projectps.cinema.mapper.RatingMapper;
import com.projectps.cinema.repository.MovieRepository;
import com.projectps.cinema.repository.RatingRepository;
import com.projectps.cinema.repository.UserRepository;
import com.projectps.cinema.service.MovieService;
import com.projectps.cinema.service.RatingService;
import com.projectps.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    RatingRepository ratingRepository;

    @Autowired
    RatingMapper ratingMapper;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    MovieService movieService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    public static double calculateScore(Movie movie) {
        double score = 0;
        for(Rating rating : movie.getRatings()) {
            score += rating.getScore();
        }
        return score / movie.getRatings().size();
    }

    @Override
    public Rating saveRating(RatingDTO ratingDTO, int userId, int movieId) {
        Rating rating = ratingMapper.toRating(ratingDTO);
        rating.setUser(userRepository.findById(userId).orElse(null));
        rating.setMovie(movieRepository.findById(movieId).orElse(null));
        Rating savedRating = ratingRepository.save(rating);
        movieService.updateMovie(movieService.getMovieById(movieId));
        return savedRating;
    }

    @Override
    public List<Rating> saveRatings(List<RatingDTO> ratingsDTO) {
        return ratingRepository.saveAll(ratingMapper.toRatingList(ratingsDTO));
    }

    @Override
    public List<RatingDTO> getRatings() {
        List<Rating> ratings = ratingRepository.findAll();
        return ratingMapper.toRatingDTOList(ratings);
    }

    @Override
    public RatingDTO getRatingById(int id) {
        Rating rating = ratingRepository.findById(id).orElse(null);
        return ratingMapper.toRatingDTO(rating);
    }

    @Override
    public List<RatingDTO> getRatingsByScore(double score) {
        List<Rating> ratings = ratingRepository.findByScoreGreaterThanEqual(score);
        return ratingMapper.toRatingDTOList(ratings);
    }

    @Override
    public Rating updateRating(RatingDTO ratingDTO) {
        Rating existingRating = ratingRepository.findById(ratingDTO.getId()).orElse(null);
        existingRating.setTitle(ratingDTO.getTitle());
        existingRating.setScore(ratingDTO.getScore());
        existingRating.setDescription(ratingDTO.getDescription());

        return ratingRepository.save(existingRating);
    }

    @Override
    public void deleteRating(int ratingId) {
        Rating rating = ratingRepository.findById(ratingId).orElse(null);
        if (rating == null) {
            return;
        }

        ratingRepository.delete(rating);

        Movie movie = movieRepository.findById(rating.getMovie().getId()).orElse(null);
        if (movie != null) {
            List<Rating> ratings = movie.getRatings();
            ratings.remove(rating);
            movie.setRatings(ratings);
            movie.setScore(calculateScore(movie));
            movieRepository.save(movie);
        }

        User user = userRepository.findById(rating.getUser().getId()).orElse(null);
        if (user != null) {
            List<Rating> userRatings = user.getRatings();
            userRatings.remove(rating);
            user.setRatings(userRatings);
            userRepository.save(user);
        }
    }
}
