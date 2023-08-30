package com.projectps.cinema.controller;

import com.projectps.cinema.DTO.RatingDTO;
import com.projectps.cinema.DTO.UserDTO;
import com.projectps.cinema.entity.Rating;
import com.projectps.cinema.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rating")
@CrossOrigin(origins = "http://localhost:4200")

public class RatingContoller {

    @Autowired
    private RatingService ratingService;

    @PostMapping("/addRating/{userId}/{movieId}")
    public Rating addRating(@RequestBody RatingDTO ratingDTO, @PathVariable int userId, @PathVariable int movieId) {
        return ratingService.saveRating(ratingDTO, userId, movieId);
    }

    @GetMapping("/allRatings")
    public List<RatingDTO> findAllRatings() {
        return ratingService.getRatings();
    }

    @GetMapping("/byId/{id}")
    public RatingDTO findRatingById(@PathVariable int id) {
        return ratingService.getRatingById(id);
    }

    @GetMapping("/byScore/{score}")
    public List<RatingDTO> findRatingsByScore(@PathVariable double score) {
        return ratingService.getRatingsByScore(score);
    }

    @PutMapping("/updateRating")
    public Rating updateRating(@RequestBody RatingDTO ratingDTO) {
        return ratingService.updateRating(ratingDTO);
    }

    @DeleteMapping("/deleteRating/{id}")
    public void deleteRating(@PathVariable int id) {
        ratingService.deleteRating(id);
    }
}
