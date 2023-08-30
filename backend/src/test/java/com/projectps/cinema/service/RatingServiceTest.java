package com.projectps.cinema.service;

import com.projectps.cinema.DTO.RatingDTO;
import com.projectps.cinema.entity.Rating;
import com.projectps.cinema.mapper.RatingMapper;
import com.projectps.cinema.repository.RatingRepository;
import com.projectps.cinema.service.impl.RatingServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class RatingServiceTest {

    @Mock
    private RatingRepository ratingRepository;

    @InjectMocks
    private RatingServiceImpl ratingService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveRating() {
        // Arrange
        RatingDTO ratingDTO = new RatingDTO();

        // Map the DTO to an entity using the RatingMapper
        Rating rating = RatingMapper.toRating(ratingDTO);

        // Mock the repository save method to return the saved rating object
        Mockito.when(ratingRepository.save(Mockito.any(Rating.class))).thenReturn(rating);

        //Act
        Rating result = ratingService.saveRating(ratingDTO, 1, 1);

        //Assert
        Assertions.assertEquals(result, rating);
    }

    @Test
    public void testSaveRatings() {
        // Arrange
        List<RatingDTO> ratingsDTO = Arrays.asList(new RatingDTO(), new RatingDTO());

        // Map the DTOs to entities using the RatingMapper
        List<Rating> ratings = RatingMapper.toRatingList(ratingsDTO);

        // Mock the repository save method to return the saved ratings object
        Mockito.when(ratingRepository.saveAll(Mockito.anyList())).thenReturn(ratings);

        // Act
        List<Rating> result = ratingService.saveRatings(ratingsDTO);

        // Assert
        Assertions.assertEquals(result, ratings);
    }

    @Test
    public void testGetRatings() {
        // Arrange
        List<Rating> ratings = Arrays.asList(new Rating(), new Rating());
        Mockito.when(ratingRepository.findAll()).thenReturn(ratings);

        // Create a list of RatingDTOs from the list of Ratings
        List<RatingDTO> ratingsDTO = RatingMapper.toRatingDTOList(ratings);

        // Act
        List<RatingDTO> result = ratingService.getRatings();

        // Assert
        Assertions.assertEquals(ratingsDTO, result);
    }

    @Test
    public void testGetRatingsByScore() {
        // Arrange
        List<Rating> ratings = Arrays.asList(new Rating(), new Rating());
        double score = 1.0;
        Mockito.when(ratingRepository.findByScoreGreaterThanEqual(score)).thenReturn(ratings);

        // Create a list of RatingDTOs from the list of Ratings
        List<RatingDTO> ratingsDTO = RatingMapper.toRatingDTOList(ratings);

        // Act
        List<RatingDTO> result = ratingService.getRatingsByScore(score);

        // Assert
        Assertions.assertEquals(ratingsDTO, result);
    }

    @Test
    public void testUpdateRating() {
        // Arrange
        RatingDTO ratingDTO = new RatingDTO();
        int id = 1;
        ratingDTO.setId(id);

        Rating existingRating = new Rating();
        existingRating.setId(id);

        Mockito.when(ratingRepository.findById(id)).thenReturn(Optional.of(existingRating));
        Mockito.when(ratingRepository.save(Mockito.any(Rating.class))).thenAnswer(i -> i.getArguments()[0]);

        // Map the DTO to an entity using the RatingMapper
        RatingMapper ratingMapper = new RatingMapper();
        Rating updateRating = ratingMapper.toRating(ratingDTO);
        updateRating.setId(id);

        // Act
        Rating result = ratingService.updateRating(ratingDTO);

        // Assert
        Assertions.assertEquals(updateRating.getId(), result.getId());
        Mockito.verify(ratingRepository, Mockito.times(1)).save(Mockito.any(Rating.class));
    }

    @Test
    public void testDeleteRating() {
        // Arrange
        Rating rating = new Rating();
        int id = 1;
        rating.setId(id);
        Mockito.when(ratingRepository.findById(rating.getId())).thenReturn(Optional.of(rating));

        // Act
        ratingService.deleteRating(id);

        // Assert
        Mockito.verify(ratingRepository, Mockito.times(1)).deleteById(id);
    }

}