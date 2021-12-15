package com.sparta.apiproject.controllers;

import com.sparta.apiproject.entities.Film;
import com.sparta.apiproject.entities.FilmText;
import com.sparta.apiproject.repositories.FilmRepository;
import com.sparta.apiproject.repositories.FilmTextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class FilmController {
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private FilmTextRepository filmTextRepository;

    @GetMapping(value="/sakila/film")
    public Film getFilmById(@RequestParam Integer id){
        return filmRepository.getById(id);
    }

    @GetMapping(value="/sakila/films")
    public List<Film> getAllFilms(){
        return filmRepository.findAll();
    }

    @PostMapping(value="/sakila/film/insert")
    public String insertFilm(@RequestBody Film film){
        if (filmRepository.existsById(film.getId())) {
            return "Film already exists";
        }
        filmRepository.save(film);
        return "Saved";
    }

    @DeleteMapping(value="/sakila/film/delete")
    public String deleteFilm(@RequestParam Integer id){
        // TODO: delete any entities depending on the current film 
        filmRepository.deleteById(id);
        return "Film with id " + id + " has been deleted";
    }

    @PutMapping(value="/sakila/film/update")
    public Film updateFilm(@RequestBody Film newState) {
        Optional<Film> oldState = filmRepository.findById(newState.getId());
        if(oldState.isEmpty())
            return null;
        filmRepository.save(newState);
        return newState;
    }

    @GetMapping(value = "/sakila/filmDescription")
    public FilmText getFilmTextById (@RequestParam Integer id) {
        return filmTextRepository.getById(id);
    }

    @GetMapping(value = "/sakila/filmsDescription")
    public List<FilmText> getAllFilmText() {
        return filmTextRepository.findAll();
    }

    @DeleteMapping(value="/sakila/filmDescription/delete")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteFilmText(@RequestParam Integer id) {
            filmTextRepository.deleteById(id);
    }

    @PutMapping(value = "/sakila/filmDescription/update")
    public FilmText updateDescription(@RequestBody FilmText newState) {
        Optional<FilmText> oldState = filmTextRepository.findById(newState.getId());
        if(oldState.isEmpty())
            return null;
        filmTextRepository.save(newState);
        return newState;
    }

    @PostMapping(value = "/sakila/filmDescription/insert")
    public FilmText insertFilmText(@RequestBody FilmText newFilmText) {
        filmTextRepository.save(newFilmText);
        return newFilmText;

    }

}
