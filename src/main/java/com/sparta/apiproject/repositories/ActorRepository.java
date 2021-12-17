package com.sparta.apiproject.repositories;

import com.sparta.apiproject.entities.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Integer> {
}