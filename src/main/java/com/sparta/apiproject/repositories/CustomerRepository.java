package com.sparta.apiproject.repositories;

import com.sparta.apiproject.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    List<Customer> findAllByStoreId(Integer id);
    Customer findByStore_Id(Integer id);
}