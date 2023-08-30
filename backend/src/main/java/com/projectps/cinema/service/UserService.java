package com.projectps.cinema.service;

import com.projectps.cinema.DTO.UserDTO;
import com.projectps.cinema.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    User saveUser(UserDTO userDTO);

    List<User> saveUsers(List<UserDTO> usersDTO);

    List<UserDTO> getUsers();

    UserDTO getUserById(int id);

    UserDTO getUserByEmail(String email);

    UserDTO getUserByUsername(String username);

    User updateUser(UserDTO userDTO);

    void deleteUser(int id);

    UserDTO addMovieToFavoritesList(int userId, int movieId);

    void removeMovieFromFavoritesList(int userId, int movieId);

    UserDTO addMovieToWatchList(int userId, int movieId);

    void removeMovieFromWatchList(int userId, int movieId);

    void setLastLogin(int userId);

    UserDTO loginUser(String username, String password);
}
