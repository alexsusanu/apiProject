package com.sparta.apiproject.entities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.sparta.apiproject.controllers.FilmController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilmTextTest {

    @Autowired
    private FilmController controller;
    @Autowired
    ObjectMapper mapper;

    @Test
    public void filmTest() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.setVisibility(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
        FilmText film = mapper.readValue(new URL("http://localhost:8080/sakila/filmDescription?id=1300"), FilmText.class);
        assertEquals("INTERSTELLAR", film.getTitle());
    }

    @Test
    public void filmIdTest() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.setVisibility(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
        FilmText film = mapper.readValue(new URL("http://localhost:8080/sakila/filmDescription?id=1300"), FilmText.class);
        assertEquals(1300, film.getId());
    }
}
