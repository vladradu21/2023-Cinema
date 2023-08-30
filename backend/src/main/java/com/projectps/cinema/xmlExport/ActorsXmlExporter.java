package com.projectps.cinema.xmlExport;

import com.projectps.cinema.DTO.ActorDTO;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ActorsXmlExporter {

    public static void exportActorsXml(List<ActorDTO> actorDTOS, String filePath) throws JAXBException, IOException {
        ActorsWrapper actorsWrapper = new ActorsWrapper(actorDTOS);

        JAXBContext jaxbContext = JAXBContext.newInstance(ActorsWrapper.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        File file = new File(filePath);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        marshaller.marshal(actorsWrapper, fileOutputStream);
        fileOutputStream.close();
    }
}
