package com.projectps.cinema.mapper;

import com.projectps.cinema.DTO.UserDTO;
import com.projectps.cinema.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {

    public static UserDTO toUserDTODecryptedPassword(User user) {
        return new UserDTO(
                user.getId(),
                user.isAdmin(),
                user.getName(),
                user.getEmail(),
                user.getUsername(),
                user.getPasswordDecrypted(),
                user.getRatings(),
                user.getFavoriteMovies(),
                user.getWatchList(),
                user.getLastLogin()
        );
    }

    public static UserDTO toUserDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.isAdmin(),
                user.getName(),
                user.getEmail(),
                user.getUsername(),
                user.getPassword(),
                user.getRatings(),
                user.getFavoriteMovies(),
                user.getWatchList(),
                user.getLastLogin()
        );
    }

    public static User toUser(UserDTO userDTO) {
        return new User(
                userDTO.getId(),
                userDTO.isAdmin(),
                userDTO.getName(),
                userDTO.getEmail(),
                userDTO.getUsername(),
                userDTO.getPassword(),
                userDTO.getRatings(),
                userDTO.getFavoriteMovies(),
                userDTO.getWatchList(),
                userDTO.getLastLogin()
        );
    }

    public static List<UserDTO> toUserDTOList(List<User> users) {
        return users.stream()
                    .map(UserMapper::toUserDTO)
                    .toList();
    }

    public static List<User> toUserList(List<UserDTO> usersDTO) {
        return usersDTO.stream()
                       .map(UserMapper::toUser)
                       .toList();
    }
}
