package dev.patika.VetManagement.dao;

import dev.patika.VetManagement.entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepo extends JpaRepository<Animal,Long> {

}
