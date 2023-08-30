package com.projectps.cinema.mapper;

import com.projectps.cinema.DTO.ActorDTO;
import com.projectps.cinema.entity.Actor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ActorMapper {

        public static ActorDTO toActorDTO(Actor actor) {
            return new ActorDTO(
                    actor.getId(),
                    actor.getName(),
                    actor.getAge(),
                    actor.getGender(),
                    actor.getOriginCountry()
            );
        }

        public static Actor toActor(ActorDTO actorDTO) {
            return new Actor(
                    actorDTO.getId(),
                    actorDTO.getName(),
                    actorDTO.getAge(),
                    actorDTO.getGender(),
                    actorDTO.getOriginCountry()
            );
        }

        public static List<ActorDTO> toActorDTOList(List<Actor> actors) {
            return actors.stream()
                        .map(ActorMapper::toActorDTO)
                        .toList();
        }

        public static List<Actor> toActorList(List<ActorDTO> actorsDTO) {
            return actorsDTO.stream()
                            .map(ActorMapper::toActor)
                            .toList();
        }
}
