package com.projectps.cinema.service.impl;

import com.projectps.cinema.DTO.UserDTO;
import com.projectps.cinema.entity.Movie;
import com.projectps.cinema.entity.User;
import com.projectps.cinema.mapper.UserMapper;
import com.projectps.cinema.repository.MovieRepository;
import com.projectps.cinema.repository.UserRepository;
import com.projectps.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public User saveUser(UserDTO userDTO) {
        return userRepository.save(userMapper.toUser(userDTO));
    }

    @Override
    public List<User> saveUsers(List<UserDTO> usersDTO) {
        return userRepository.saveAll(userMapper.toUserList(usersDTO));
    }

    @Override
    public List<UserDTO> getUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.toUserDTOList(users);
    }

    @Override
    public UserDTO getUserById(int id) {
        User user = userRepository.findById(id).orElse(null);
        return userMapper.toUserDTO(user);
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return userMapper.toUserDTODecryptedPassword(user);
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return userMapper.toUserDTO(user);
    }

    @Override
    public User updateUser(UserDTO userDTO) {
        User existingUser = userRepository.findById(userDTO.getId()).orElse(null);
        existingUser.setAdmin(userDTO.isAdmin());
        existingUser.setName(userDTO.getName());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setUsername(userDTO.getUsername());
        existingUser.setPassword(userDTO.getPassword());
        existingUser.setRatings(userDTO.getRatings());
        existingUser.setFavoriteMovies(userDTO.getFavoriteMovies());
        existingUser.setWatchList(userDTO.getWatchList());
        existingUser.setLastLogin(userDTO.getLastLogin());
        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDTO addMovieToFavoritesList(int userId, int movieId) {
        UserDTO userDTO = getUserById(userId);
        Movie movie = movieRepository.findById(movieId).orElse(null);

        if(userDTO != null && movie != null) {
            List<Movie> favoriteMovies = new ArrayList<>(userDTO.getFavoriteMovies());
            favoriteMovies.add(movie);
            userDTO.setFavoriteMovies(favoriteMovies);
            updateUser(userDTO);
        }
        return userDTO;
    }

    @Override
    public void removeMovieFromFavoritesList(int userId, int movieId) {
        UserDTO userDTO = getUserById(userId);
        Movie movie = movieRepository.findById(movieId).orElse(null);

        if(userDTO != null && movie != null) {
            List<Movie> favoriteMovies = new ArrayList<>(userDTO.getFavoriteMovies());
            favoriteMovies.remove(movie);
            userDTO.setFavoriteMovies(favoriteMovies);
            updateUser(userDTO);
        }
    }

    @Override
    public UserDTO addMovieToWatchList(int userId, int movieId) {
        UserDTO userDTO = getUserById(userId);
        Movie movie = movieRepository.findById(movieId).orElse(null);

        if(userDTO != null && movie != null) {
            List<Movie> watchList = new ArrayList<>(userDTO.getWatchList());
            watchList.add(movie);
            userDTO.setWatchList(watchList);
            updateUser(userDTO);
        }
        return userDTO;
    }

    @Override
    public void removeMovieFromWatchList(int userId, int movieId) {
        UserDTO userDTO = getUserById(userId);
        Movie movie = movieRepository.findById(movieId).orElse(null);

        if(userDTO != null && movie != null) {
            List<Movie> watchList = new ArrayList<>(userDTO.getWatchList());
            watchList.remove(movie);
            userDTO.setWatchList(watchList);
            updateUser(userDTO);
        }
    }

    @Override
    public void setLastLogin(int userId) {
        UserDTO userDTO = getUserById(userId);
        userDTO.setLastLogin(ZonedDateTime.now());
        updateUser(userDTO);
    }

    @Override
    public UserDTO loginUser(String username, String password) {
       UserDTO userDTO = getUserByUsername(username);
       userDTO.setLastLogin(ZonedDateTime.now());
       updateUser(userDTO);

       return userDTO;
    }
}
