package com.sparta.apiproject.controllers;

import com.sparta.apiproject.entities.Staff;
import com.sparta.apiproject.repositories.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StaffController {
    @Autowired
    private StaffRepository staffRepository;
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
    @DeleteMapping(value = "/sakila/staff/delete")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteStaff(@RequestParam Integer id) {
        staffRepository.deleteById(id);
    }
    @PostMapping(value = "/sakila/staff/add")
    public Staff insertStaff(@RequestBody Staff newStaff) {
        staffRepository.save(newStaff);
        return newStaff;
    }

}
