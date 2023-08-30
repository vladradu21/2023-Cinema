package com.projectps.cinema.service;

import com.projectps.cinema.DTO.ActorDTO;
import com.projectps.cinema.entity.Actor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ActorService {

    Actor saveActor(ActorDTO actorDTO);

    List<Actor> saveActors(List<ActorDTO> actorsDTO);

    List<ActorDTO> getActors();

    ActorDTO getActorById(int id);

    Actor updateActor(ActorDTO actorDTO);

    void deleteActor(int id);
}
