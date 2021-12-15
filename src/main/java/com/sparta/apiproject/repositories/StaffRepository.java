package com.sparta.apiproject.repositories;

import com.sparta.apiproject.entities.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, Integer> {
}