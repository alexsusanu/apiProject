package com.sparta.apiproject.repositories;

import com.sparta.apiproject.entities.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Integer> {
}
