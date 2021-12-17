package com.sparta.apiproject.controllers;

import com.sparta.apiproject.entities.Staff;
import com.sparta.apiproject.entities.Store;
import com.sparta.apiproject.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StoreController{
    @Autowired
    private StoreRepository storeRepository;
    @GetMapping(value = "/sakila/store")
    public Store getStore(@RequestParam Integer id) {
        Optional<Store> result = storeRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            return null;
        }
    }

    @GetMapping(value = "/sakila/stores")
    public List<Store> getStores() {
        List<Store> result = storeRepository.findAll();
        return result;
    }

    @PutMapping(value = "/sakila/store/update")
    public Store updateStore(@RequestBody Store newState) {
        Optional<Store> oldState = storeRepository.findById(newState.getId());
        if (oldState.isEmpty()) {
            return null;
        }
        storeRepository.save(newState);
        return newState;
    }

    @PostMapping(value = "/sakila/store/add")
    public Store insertStore(@RequestBody Store newStore) {
        storeRepository.save(newStore);
        return newStore;
    }
}
