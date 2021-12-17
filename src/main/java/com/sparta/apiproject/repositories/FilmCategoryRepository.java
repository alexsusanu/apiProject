package com.sparta.apiproject.repositories;

import com.sparta.apiproject.entities.FilmCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmCategoryRepository extends JpaRepository<FilmCategory, Integer> {
}
