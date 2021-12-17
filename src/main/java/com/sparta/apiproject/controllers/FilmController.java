package com.sparta.apiproject.controllers;

import com.sparta.apiproject.entities.*;
import com.sparta.apiproject.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
public class FilmController {
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private FilmTextRepository filmTextRepository;
    @Autowired
    private FilmActorRepository filmActorRepository;
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private FilmCategoryRepository filmCategoryRepository;
    @Autowired
    private RentalRepository rentalRepository;
    @Autowired
    private PaymentRepository paymentRepository;

    @GetMapping(value="/sakila/film")
    public Film getFilmById(@RequestParam Integer id){
        return filmRepository.getById(id);
    }

    @GetMapping(value="/sakila/films")
    public List<Film> getAllFilms(){
        return filmRepository.findAll();
    }

    @PostMapping(value="/sakila/film/insert")
    public String insertFilm(@RequestBody Film film){
        if (filmRepository.existsById(film.getId())) {
            return "Film already exists";
        }
        filmRepository.save(film);
        return "Saved";
    }

    @DeleteMapping(value="/sakila/film/delete")
    @Transactional
    public String deleteFilm(@RequestParam Integer id){
        List<FilmText> filmTextToDelete = new ArrayList<>();
        for(FilmText filmText: filmTextRepository.findAll()){
            if(filmText.equals(id)){
                filmTextToDelete.add(filmText);
            }
        }
        filmTextRepository.deleteAll(filmTextToDelete);

        List<FilmActor> filmActorsToDelete = new ArrayList<>();
        for(FilmActor filmActor: filmActorRepository.findAll()){
            Integer filmId = filmActor.getId().getFilmId();
            if(Objects.equals(id, filmId)){
                filmActorsToDelete.add(filmActor);
            }
        }
        filmActorRepository.deleteAll(filmActorsToDelete);


        List<Inventory> inventoriesToDelete = new ArrayList<>();
        for(Inventory inventory: inventoryRepository.findAll()){
            Integer inventoryFilmId = inventory.getFilm().getId();
            if(id.equals(inventoryFilmId)){
                inventoriesToDelete.add(inventory);
            }
        }

        List<FilmCategory> filmCategoriesToDelete = new ArrayList<>();
        for(FilmCategory filmCategory: filmCategoryRepository.findAll()){
            FilmCategoryId filmCategoryId = filmCategory.getId();
            if(id.equals(filmCategoryId.getFilmId())){
                filmCategoriesToDelete.add(filmCategory);
            }
        }
        filmCategoryRepository.deleteAll(filmCategoriesToDelete);


        List<Rental> rentalsToDelete = new ArrayList<>();
        for(Rental rental: rentalRepository.findAll()){
            Integer rentalInventoryId = rental.getInventory().getId();
            for(Inventory inventory: inventoriesToDelete){
                Integer inventoryId = inventory.getId();
                if(inventoryId.equals(rentalInventoryId)){
                    rentalsToDelete.add(rental);
                }
            }
        }

        List<Payment> paymentsToDelete = new ArrayList<>();
        for(Payment payment: paymentRepository.findAll()){
            if(payment.getRental() != null){
                Integer rentalId = payment.getRental().getId();
                for(Rental rental: rentalsToDelete){
                    if(rentalId.equals(rental.getId())){
                        paymentsToDelete.add(payment);

                    }
                }
            }
        }

        paymentRepository.deleteAll(paymentsToDelete);

        rentalRepository.deleteAll(rentalsToDelete);

        inventoryRepository.deleteAll(inventoriesToDelete);

        filmRepository.deleteById(id);
        return "Film with id " + id + " has been deleted";
    }

    @PutMapping(value="/sakila/film/update")
    public Film updateFilm(@RequestBody Film newState) {
        Optional<Film> oldState = filmRepository.findById(newState.getId());
        if(oldState.isEmpty())
            return null;
        filmRepository.save(newState);
        return newState;
    }

    @GetMapping(value = "/sakila/filmDescription")
    public FilmText getFilmTextById (@RequestParam Integer id) {
        return filmTextRepository.getById(id);
    }

    @GetMapping(value = "/sakila/filmsDescription")
    public List<FilmText> getAllFilmText() {
        return filmTextRepository.findAll();
    }

    @DeleteMapping(value="/sakila/filmDescription/delete")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteFilmText(@RequestParam Integer id) {
            filmTextRepository.deleteById(id);
    }

    @PutMapping(value = "/sakila/filmDescription/update")
    public FilmText updateDescription(@RequestBody FilmText newState) {
        Optional<FilmText> oldState = filmTextRepository.findById(newState.getId());
        if(oldState.isEmpty())
            return null;
        filmTextRepository.save(newState);
        return newState;
    }

    @PostMapping(value = "/sakila/filmDescription/insert")
    public FilmText insertFilmText(@RequestBody FilmText newFilmText) {
        filmTextRepository.save(newFilmText);
        return newFilmText;

    }

}
