package com.projectps.cinema.service.impl;

import com.projectps.cinema.DTO.ActorDTO;
import com.projectps.cinema.entity.Actor;
import com.projectps.cinema.mapper.ActorMapper;
import com.projectps.cinema.repository.ActorRepository;
import com.projectps.cinema.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private ActorMapper actorMapper;

    @Override
    public Actor saveActor(ActorDTO actorDTO) {
        return actorRepository.save(actorMapper.toActor(actorDTO));
    }

    @Override
    public List<Actor> saveActors(List<ActorDTO> actorsDTO) {
        return actorRepository.saveAll(actorMapper.toActorList(actorsDTO));
    }

    @Override
    public List<ActorDTO> getActors() {
        List<Actor> actors = actorRepository.findAll();
        return actorMapper.toActorDTOList(actors);
    }

    @Override
    public ActorDTO getActorById(int id) {
        Actor actor = actorRepository.findById(id).orElse(null);
        return actorMapper.toActorDTO(actor);
    }

    @Override
    public Actor updateActor(ActorDTO actorDTO) {
        Actor existingActor = actorRepository.findById(actorDTO.getId()).get();
        existingActor.setName(actorDTO.getName());
        existingActor.setAge(actorDTO.getAge());
        existingActor.setGender(actorDTO.getGender());
        existingActor.setOriginCountry(actorDTO.getOriginCountry());
        return actorRepository.save(existingActor);
    }

    @Override
    public void deleteActor(int id) {
        actorRepository.deleteById(id);
    }
}
