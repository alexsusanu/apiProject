package com.sparta.apiproject.repositories;

import com.sparta.apiproject.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Integer> {
    List<Store> findAllByManagerStaffId(Integer id);
}