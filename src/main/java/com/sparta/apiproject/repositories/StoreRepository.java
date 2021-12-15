package com.sparta.apiproject.repositories;

import com.sparta.apiproject.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Integer>
{
}
