package com.projectps.cinema.service;

import com.projectps.cinema.DTO.ActorDTO;
import com.projectps.cinema.entity.Actor;
import com.projectps.cinema.mapper.ActorMapper;
import com.projectps.cinema.repository.ActorRepository;
import com.projectps.cinema.service.impl.ActorServiceImpl;
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

class ActorServiceTest {

    @Mock
    private ActorRepository actorRepository;

    @InjectMocks
    private ActorServiceImpl actorService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveActor() {
        // Arrange
        ActorDTO actorDTO = new ActorDTO();

        // Map the DTO to an entity using the ActorMapper
        Actor actor = ActorMapper.toActor(actorDTO);

        // Mock the repository save method to return the saved actor object

        Mockito.when(actorRepository.save(Mockito.any(Actor.class))).thenReturn(actor);

        // Act
        Actor result = actorService.saveActor(actorDTO);

        // Assert
        Assertions.assertEquals(actor, result);
    }

    @Test
    public void testSaveActors() {
        //Arrange
        List<ActorDTO> actorsDTO = Arrays.asList(new ActorDTO(), new ActorDTO());

        // Map the DTOs to entities using the ActorMapper
        List<Actor> actors = ActorMapper.toActorList(actorsDTO);

        //Mock the repository save method to return the saved actors object
        Mockito.when(actorRepository.saveAll(Mockito.anyList())).thenReturn(actors);

        //Act
        List<Actor> result = actorService.saveActors(actorsDTO);

        //Assert
        Assertions.assertEquals(actors, result);
    }

    @Test
    public void testGetActors() {
        // Arrange
        List<Actor> actors = Arrays.asList(new Actor(), new Actor());
        Mockito.when(actorRepository.findAll()).thenReturn(actors);

        // Create a list of DTOs from the list of entities
        List<ActorDTO> actorDTOS = ActorMapper.toActorDTOList(actors);

        // Act
        List<ActorDTO> result = actorService.getActors();

        // Assert
        Assertions.assertEquals(actorDTOS, result);
    }

    @Test
    public void testGetActorById() {
        // Arrange
        Actor actor = new Actor();
        int id = 1;
        actor.setId(id);
        Mockito.when(actorRepository.findById(id)).thenReturn(Optional.of(actor));
        ActorDTO actorDTO = ActorMapper.toActorDTO(actor);

        // Act
        ActorDTO result = actorService.getActorById(id);

        // Assert
        Assertions.assertEquals(actorDTO, result);
    }

    @Test
    public void testUpdateActor() {
        // Arrange
        ActorDTO actorDTO = new ActorDTO();
        int id = 1;
        actorDTO.setId(id);

        Actor existingActor = new Actor();
        existingActor.setId(id);

        Mockito.when(actorRepository.findById(id)).thenReturn(Optional.of(existingActor));
        Mockito.when(actorRepository.save(Mockito.any(Actor.class))).thenAnswer(i -> i.getArguments()[0]);

        // Map the DTO to an entity using the ActorMapper
        ActorMapper actorMapper = new ActorMapper();
        Actor updatedActor = actorMapper.toActor(actorDTO);
        updatedActor.setId(id);

        // Act
        Actor result = actorService.updateActor(actorDTO);

        // Assert
        Assertions.assertEquals(updatedActor.getId(), result.getId());
        Mockito.verify(actorRepository, Mockito.times(1)).save(Mockito.any(Actor.class));
    }

    @Test
    public void testDeleteActor() {
        // Arrange
        Actor actor = new Actor();
        int id = 1;
        actor.setId(id);
        Mockito.when(actorRepository.findById(id)).thenReturn(Optional.of(actor));

        // Act
        actorService.deleteActor(id);

        // Assert
        Mockito.verify(actorRepository, Mockito.times(1)).deleteById(id);
    }
}