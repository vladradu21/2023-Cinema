package com.projectps.cinema.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectps.cinema.DTO.MovieDTO;
import com.projectps.cinema.entity.Notification;
import com.projectps.cinema.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MyWebSocketHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    @Autowired
    private MovieService movieService;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        List<MovieDTO> popularMovies = movieService.getMoviesByPopularity();

        for (int i = 0; i < 10000; i++) {
            for(MovieDTO movieDTO : popularMovies) {
                if(movieDTO.getPopularity() == 0) {
                    break;
                }
                Notification notification = new Notification("Current popular movies", movieDTO.getTitle() + "\npopularity: " + movieDTO.getPopularity());

                TextMessage message = new TextMessage(objectMapper.writeValueAsString(notification));
                session.sendMessage(message);
                Thread.sleep(2000);
            }
        }

        sessions.add(session);
    }
}
