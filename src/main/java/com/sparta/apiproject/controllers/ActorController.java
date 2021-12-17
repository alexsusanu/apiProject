package com.sparta.apiproject.controllers;

import com.sparta.apiproject.entities.Actor;
import com.sparta.apiproject.repositories.ActorRepository;
import com.sparta.apiproject.repositories.FilmActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ActorController {

    @Autowired
    private ActorRepository actorRepository;
    @Autowired
    private FilmActorRepository filmActorRepository;

    @GetMapping(value = "/sakila/actor")
    public Actor getActor(@RequestParam Integer id) {
        Optional<Actor> result = actorRepository.findById(id);
        if (result.isPresent())
            return result.get();
        else
            return null;
    }

    @PostMapping(value = "/sakila/actor/insert")
    public Actor insertFilmText(@RequestBody Actor newActor) {
        actorRepository.save(newActor);
        return newActor;

    }

}
