package com.sparta.apiproject.repositories;

import com.sparta.apiproject.entities.FilmText;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmTextRepository extends JpaRepository<FilmText, Integer> {
}