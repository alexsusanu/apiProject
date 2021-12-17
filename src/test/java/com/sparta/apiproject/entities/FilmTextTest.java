package com.sparta.apiproject.entities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.sparta.apiproject.controllers.FilmController;
import com.sparta.apiproject.repositories.FilmTextRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilmTextTest {

    @Autowired
    private FilmText filmText;

    @Test
    public void filmTest() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        filmText = mapper.readValue(new URL("http://localhost:8080/sakila/filmDescription?id=1"), FilmText.class);
        assertEquals("ACADEMY DINOSAUR", filmText.getTitle());
    }

    @Test
    public void filmIdTest() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        FilmText film = mapper.readValue(new URL("http://localhost:8080/sakila/filmDescription?id=1"), FilmText.class);
        assertEquals(1, film.getId());
    }


}
