package com.projectps.cinema.controller;

import com.projectps.cinema.DTO.ActorDTO;
import com.projectps.cinema.entity.Actor;
import com.projectps.cinema.service.ActorService;
import com.projectps.cinema.xmlExport.ActorsXmlExporter;
import jakarta.xml.bind.JAXBException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/actor")
public class ActorController {

    @Autowired
    private ActorService actorService;

    @PostMapping("/addActor")
    public Actor addActor(@RequestBody ActorDTO actorDTO) {
        return actorService.saveActor(actorDTO);
    }

    @PostMapping("/addActors")
    public List<Actor> addActors(@RequestBody List<ActorDTO> actorsDTO) {
        return actorService.saveActors(actorsDTO);
    }

    @GetMapping("/allActors")
    public List<ActorDTO> findAllActors() {
        return actorService.getActors();
    }

    @GetMapping("/byId/{id}")
    public ActorDTO findActorById(@PathVariable int id) {
        return actorService.getActorById(id);
    }

    @PutMapping("/updateActor")
    public Actor updateActor(@RequestBody ActorDTO actorDTO) {
        return actorService.updateActor(actorDTO);
    }

    @DeleteMapping("/deleteActor/{id}")
    public void deleteActor(@PathVariable int id) {
        actorService.deleteActor(id);
    }

    @GetMapping("/exportActorsXML")
    public void exportActorsToXml() throws JAXBException, IOException {
        List<ActorDTO> actorDTOS = actorService.getActors();

        File file = new File("src/main/resources/actors.xml");
        ActorsXmlExporter.exportActorsXml(actorDTOS, file.getAbsolutePath());
    }
}
