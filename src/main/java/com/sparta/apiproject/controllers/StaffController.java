package com.sparta.apiproject.controllers;

import com.sparta.apiproject.entities.*;
import com.sparta.apiproject.repositories.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StaffController {
    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping(value = "/sakila/allstaff")
    public List<Staff> getAllStaff() { return staffRepository.findAll(); }

    @GetMapping(value = "/sakila/staff")
    public Staff getStaff(@RequestParam Integer id) {
        Optional<Staff> result = staffRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            return null;
        }
    }
    @PutMapping(value = "/sakila/staff/update")
    public Staff updateStaff(@RequestBody Staff newState) {
        Optional<Staff> oldState = staffRepository.findById(newState.getId());
        if (oldState.isEmpty()) {
            return null;
        }
        staffRepository.save(newState);
        return newState;
    }


    @Cascade(CascadeType.DELETE)
    @DeleteMapping(value = "/sakila/staff/delete")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteStaff(@RequestParam Integer id)
    {
        List<Payment> list = paymentRepository.findAllByStaff_Id(id);
        for(Payment payment : list)
        {
            paymentRepository.deleteById(payment.getId());
        }

        List<Rental> rentalList = rentalRepository.findAllByStaff_Id(id);
        for(Rental rental : rentalList)
        {
            System.out.println("Delete rental with id: " + rental.getId());
            rentalRepository.deleteById(rental.getId());
        }
        List<Store> storeList = storeRepository.findAllByManagerStaff_Id(id);
        for(Store store : storeList)
        {
            System.out.println("Delete store with id: " + store.getId());
            List<Customer> customerList = customerRepository.findAllByStore_Id(store.getId());
            for(Customer customer : customerList)
            {
                customerRepository.deleteById(customer.getId());
            }
            storeRepository.deleteById(store.getId());
        }
        staffRepository.deleteById(id);
    }
    @PostMapping(value = "/sakila/staff/add")
    public Staff insertStaff(@RequestBody Staff newStaff) {
        staffRepository.save(newStaff);
        return newStaff;
    }

}
