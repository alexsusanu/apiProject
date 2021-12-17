package com.sparta.apiproject;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sparta.apiproject.controllers.FilmController;
import com.sparta.apiproject.entities.Film;
import com.sparta.apiproject.repositories.FilmRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class FilmTest {
    private static ObjectMapper mapper;
    @BeforeAll
    public static void setup(){
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.setVisibility(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
    }

    @Test
    @DisplayName("Getting the film with index 1 and confirming it's name")
    public void getFilm(){
        try {
            Film film = mapper.readValue(new URL("http://localhost:8080/sakila/film?id=1"), Film.class);
            assert(film.getTitle().equals("ACADEMY DINOSAUR"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @Test
//    public void getFilms(){
//        try {
//
//            List<Film> films = mapper.readValues(new URL("http://localhost:8080/sakila/film?id=1"), Film.class);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//    }
}
