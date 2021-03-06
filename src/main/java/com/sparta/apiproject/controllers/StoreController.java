package com.sparta.apiproject.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.apiproject.entities.Film;
import com.sparta.apiproject.entities.Staff;
import com.sparta.apiproject.entities.Store;
import com.sparta.apiproject.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StoreController{
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    ObjectMapper objectMapper;
    @GetMapping(value = "/sakila/store")
    public ResponseEntity<String> getStoreById(@RequestParam Integer id) {
        Optional<Store> result = storeRepository.findById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type", "application/json; charset=utf-8");
        if (result.isPresent()) {
            try {
                ResponseEntity<String> resp = new ResponseEntity<String>(objectMapper.writeValueAsString(result.get()), headers, HttpStatus.OK);
                return resp;
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<String>("{\"message\": \"That actor doesnt exist\"}", headers,HttpStatus.OK);
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
