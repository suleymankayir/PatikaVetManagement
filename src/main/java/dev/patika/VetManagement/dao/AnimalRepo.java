package dev.patika.VetManagement.dao;

import dev.patika.VetManagement.entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnimalRepo extends JpaRepository<Animal,Long> {
    Animal findByName(String name);


    Optional<Animal> findByNameAndSpeciesAndBreedAndGenderAndColor(String name, String species, String breed, String gender, String color);
}
