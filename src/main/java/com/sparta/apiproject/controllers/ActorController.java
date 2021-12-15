package com.sparta.apiproject.controllers;

import com.sparta.apiproject.entities.Actor;
import com.sparta.apiproject.entities.FilmActor;
import com.sparta.apiproject.entities.FilmActorId;
import com.sparta.apiproject.repositories.ActorRepository;
import com.sparta.apiproject.repositories.FilmActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

public class ActorController {
    @Autowired
    private ActorRepository actorRepository;
    @Autowired
    private FilmActorRepository filmActorRepository;

    @GetMapping(value = "/sakila/actors")
    public List<Actor> getActors()
    {
        return actorRepository.findAll();
    }

    @GetMapping(value = "/sakila/actor")
    public Optional<Actor> getActor(@RequestParam Integer id)
    {
        Optional<Actor> result = actorRepository.findById(id);
        return result;
    }

    @PutMapping(value = "/sakila/actor/update")
    public Actor updateActor(@RequestBody Actor newState)
    {
        // Get original State  // Store the new state in DB
        Optional<Actor> oldState = actorRepository.findById(newState.getId());
        if(oldState.isEmpty()) return null;
        actorRepository.save(newState);
        return newState;
    }
    @DeleteMapping(value="/sakila/actor/delete")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteActor(@RequestParam Integer id) {
        actorRepository.deleteById(id);
    }

    @PostMapping(value = "/sakila/actor/add")
    public Actor insertActor(@RequestBody Actor newActor)
    {
        actorRepository.save(newActor);
        return newActor;
    }

    @GetMapping(value="/example")
    public ResponseEntity<String> getResponse()
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).header("content-type", "whatever").body("This is my return value.");
    }

    @GetMapping(value = "/sakila/actorTEST")
    public Optional<FilmActor> getFilmActor(@RequestParam Integer id)
    {
        Optional<FilmActor> result = filmActorRepository.findById(id);
        return result;
    }

    /*
    @GetMapping(value = "/sakila/actor")
    public Optional<Actor> getActor(@RequestParam Integer id)
    {
        Optional<Actor> result = actorRepository.findById(id);
        return result;
    }

     */
}
