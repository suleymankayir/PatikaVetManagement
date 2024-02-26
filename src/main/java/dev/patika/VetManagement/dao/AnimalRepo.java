package dev.patika.VetManagement.dao;

import dev.patika.VetManagement.entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepo extends JpaRepository<Animal,Long> {
    Animal findByName(String name);


}
