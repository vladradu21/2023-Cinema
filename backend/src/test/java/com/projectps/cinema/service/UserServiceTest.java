package com.projectps.cinema.service;

import com.projectps.cinema.DTO.UserDTO;
import com.projectps.cinema.entity.User;
import com.projectps.cinema.mapper.UserMapper;
import com.projectps.cinema.repository.UserRepository;
import com.projectps.cinema.service.impl.UserServiceImpl;
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

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveUser() {
        // Arrange
        UserDTO userDTO = new UserDTO();

        //Map the DTO to an entity using the UserMapper
        User user = UserMapper.toUser(userDTO);

        //Mock the repository save method to return the saved user object
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        //Act
        User result = userService.saveUser(userDTO);

        //Assert
        Assertions.assertEquals(result, user);
    }

    @Test
    public void testGetUsers() {
        // Arrange
        List<User> users = Arrays.asList(new User(), new User());
        Mockito.when(userRepository.findAll()).thenReturn(users);

        // Create a list of DTOs from the list of entities
        List<UserDTO> usersDTOs = UserMapper.toUserDTOList(users);

        // Act
        List<UserDTO> result = userService.getUsers();

        // Assert
        Assertions.assertEquals(usersDTOs, result);
    }

    @Test
    public void testGetUserById() {
        // Arrange
        User user = new User();
        int id = 1;
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(user));
        UserDTO userDTO = UserMapper.toUserDTO(user);

        // Act
        UserDTO result = userService.getUserById(id);

        // Assert
        Assertions.assertEquals(userDTO, result);
    }


    @Test
    public void testUpdateUser() {
        // Arrange
        UserDTO userDTO = new UserDTO();
        int id = 1;
        userDTO.setId(id);

        User existingUser = new User();
        existingUser.setId(id);

        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(existingUser));
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenAnswer(i -> i.getArguments()[0]);

        //Map the DTO to an entity using the UserMapper
        UserMapper userMapper = new UserMapper();
        User updatedUser = userMapper.toUser(userDTO);
        updatedUser.setId(id);

        //Act
        User result = userService.updateUser(userDTO);

        //Assert
        Assertions.assertEquals(updatedUser.getId(), result.getId());
        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any(User.class));
    }

    @Test
    public void testDeleteUser() {
        // Arrange
        User user = new User();
        int id = 1;
        user.setId(id);
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(user));

        // Act
        userService.deleteUser(id);

        // Assert
        Mockito.verify(userRepository, Mockito.times(1)).deleteById(id);
    }

}