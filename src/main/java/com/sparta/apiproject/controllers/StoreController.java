package com.sparta.apiproject.controllers;

import com.sparta.apiproject.entities.Store;
import com.sparta.apiproject.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StoreController
{
    @Autowired
    private StoreRepository repository;

    @GetMapping(value="sakila/stores")
    public List<Store> getStores()
    {
        return repository.findAll();
    }

    @GetMapping(value= "/sakila/store")
    public Store getStore(@RequestParam Integer id)
    {
        Optional<Store> result = repository.findById(id);
        return result.isPresent() ? result.get() : null;
    }

    @PutMapping(value= "/sakila/store/update")
    public Store updateStore(@RequestBody Store newStore)
    {
        Optional<Store> old = repository.findById(newStore.getId());
        if(old.isEmpty()) return null;
        repository.save(newStore);
        return newStore;
    }

    @DeleteMapping(value="/sakila/store/delete")
    @ResponseStatus(value= HttpStatus.NO_CONTENT)
    public void deleteStore(@RequestParam Integer id)
    {
        repository.deleteById(id);
    }

    @PostMapping(value="/sakila/store/add")
    public Store insertStore(@RequestBody Store newStore)
    {
        repository.save(newStore);
        return newStore;
    }
}
