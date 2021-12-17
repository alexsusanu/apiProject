package com.sparta.apiproject.repositories;

import com.sparta.apiproject.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    Payment findByStaffId(Integer id);

    List<Payment> findAllByStaffId(Integer id);
}