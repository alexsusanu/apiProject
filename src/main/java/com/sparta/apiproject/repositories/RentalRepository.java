package com.sparta.apiproject.repositories;

import com.sparta.apiproject.entities.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Integer>
{
    List<Rental> findAllByStaff_Id(Integer id);
}