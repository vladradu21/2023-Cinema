package com.projectps.cinema.xmlExport;

import com.projectps.cinema.DTO.ActorDTO;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "actors")
public class ActorsWrapper {

    private List<ActorDTO> actors;

    @XmlElement(name = "actor")
    public List<ActorDTO> getActors() {
        return actors;
    }

    public void setActors(List<ActorDTO> actors) {
        this.actors = actors;
    }
}
