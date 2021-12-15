package com.sparta.apiproject.repositories;

import com.sparta.apiproject.entities.FilmActor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmActorRepository extends JpaRepository<FilmActor, Integer> {
}
