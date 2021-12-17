package com.sparta.apiproject.repositories;

import com.sparta.apiproject.entities.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
}