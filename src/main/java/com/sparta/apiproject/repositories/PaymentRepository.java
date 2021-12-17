package com.sparta.apiproject.repositories;

import com.sparta.apiproject.entities.Payment;
import com.sparta.apiproject.entities.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Integer>
{
    Payment findByStaff_Id(Integer id);

    List<Payment> findAllByStaff_Id(Integer id);
}