package com.projectps.cinema.service;

import com.projectps.cinema.DTO.MovieDTO;
import com.projectps.cinema.entity.Movie;
import com.projectps.cinema.mapper.MovieMapper;
import com.projectps.cinema.repository.MovieRepository;
import com.projectps.cinema.service.impl.MovieServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieServiceImpl movieService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveMovie() {
        //Arrange
        MovieDTO movieDTO = new MovieDTO();

        // Map the DTO to an entity using the MovieMapper
        Movie movie = MovieMapper.toMovie(movieDTO);

        //Mock the repository save method to return the saved movie object
        Mockito.when(movieRepository.save(Mockito.any(Movie.class))).thenReturn(movie);

        //Act
        Movie result = movieService.saveMovie(movieDTO);

        //Assert
        Assertions.assertEquals(result, movie);
    }

    @Test
    public void testSaveMovies() {
        // Arrange
        List<MovieDTO> moviesDTO = Arrays.asList(new MovieDTO(), new MovieDTO());

        // Map the DTOs to entities using the MovieMapper
        List<Movie> movies = MovieMapper.toMovieList(moviesDTO);

        // Mock the repository saveAll method to return the saved movies
        Mockito.when(movieRepository.saveAll(Mockito.anyList())).thenReturn(movies);

        // Act
        List<Movie> result = movieService.saveMovies(moviesDTO);

        // Assert
        Assertions.assertEquals(result, movies);
    }

    @Test
    public void testGetMovies() {
        // Arrange
        List<Movie> movies = Arrays.asList(new Movie(), new Movie());
        Mockito.when(movieRepository.findAll()).thenReturn(movies);

        // Create a list of expected DTOs by mapping the entities using MovieMapper
        List<MovieDTO> expectedDTOs = MovieMapper.toMovieDTOList(movies);

        // Act
        List<MovieDTO> result = movieService.getMovies();

        // Assert
        Assertions.assertEquals(expectedDTOs, result);
    }

    @Test
    public void testGetMovieById() {
        // Arrange
        Movie movie = new Movie();
        int id = 1;
        movie.setId(id);
        Mockito.when(movieRepository.findById(id)).thenReturn(Optional.of(movie));
        MovieDTO expectedDTO = MovieMapper.toMovieDTO(movie);

        // Act
        MovieDTO result = movieService.getMovieById(id);

        // Assert
        Assertions.assertEquals(expectedDTO, result);
    }

    @Test
    public void testGetMoviesByGenre() {
        // Arrange
        List<Movie> movies = Arrays.asList(new Movie(), new Movie());
        String genre = "Action";
        Mockito.when(movieRepository.findByGenresContaining(genre)).thenReturn(movies);

        // Create a list of expected DTOs by mapping the entities using MovieMapper
        List<MovieDTO> expectedDTOs = MovieMapper.toMovieDTOList(movies);

        // Act
        List<MovieDTO> result = movieService.getMoviesByGenre(genre);

        // Assert
        Assertions.assertEquals(expectedDTOs, result);
    }

    @Test
    public void testGetMoviesByScore() {
        // Arrange
        List<Movie> movies = Arrays.asList(new Movie(), new Movie());
        double score = 7.0;
        Mockito.when(movieRepository.findByScoreGreaterThanEqual(score)).thenReturn(movies);

        // Create a list of expected DTOs by mapping the entities using MovieMapper
        List<MovieDTO> expectedDTOs = MovieMapper.toMovieDTOList(movies);

        // Act
        List<MovieDTO> result = movieService.getMoviesByScore(score);

        // Assert
        Assertions.assertEquals(expectedDTOs, result);
    }

    @Test
    public void testGetMoviesByYear() {
        // Arrange
        List<Movie> movies = Arrays.asList(new Movie(), new Movie());
        int year = 2010;
        Mockito.when(movieRepository.findByYearGreaterThanEqual(year)).thenReturn(movies);

        // Create a list of expected DTOs by mapping the entities using MovieMapper
        List<MovieDTO> expectedDTOs = MovieMapper.toMovieDTOList(movies);

        // Act
        List<MovieDTO> result = movieService.getMoviesByYear(year);

        // Assert
        Assertions.assertEquals(expectedDTOs, result);
    }

    @Test
    public void testUpdateMovie() {
        // Arrange
        MovieDTO movieDTO = new MovieDTO();
        int id = 1;
        movieDTO.setId(id);

        Movie existingMovie = new Movie();
        existingMovie.setId(id);

        Mockito.when(movieRepository.findById(id)).thenReturn(Optional.of(existingMovie));
        Mockito.when(movieRepository.save(Mockito.any(Movie.class))).thenAnswer(i -> i.getArguments()[0]);

        // Map the DTO to an entity using the MovieMapper
        MovieMapper movieMapper = new MovieMapper();
        Movie updatedMovie = movieMapper.toMovie(movieDTO);
        updatedMovie.setId(id);

        // Act
        Movie result = movieService.updateMovie(movieDTO);

        // Assert
        Assertions.assertEquals(updatedMovie.getId(), result.getId());
        Mockito.verify(movieRepository, Mockito.times(1)).save(Mockito.any(Movie.class));
    }

    @Test
    public void testDeleteMovie() {
        // Arrange
        Movie movie = new Movie();
        int id = 1;
        movie.setId(id);
        Mockito.when(movieRepository.findById(id)).thenReturn(Optional.of(movie));

        // Act
        movieService.deleteMovie(id);

        // Assert
        Mockito.verify(movieRepository, Mockito.times(1)).deleteById(id);
    }
}