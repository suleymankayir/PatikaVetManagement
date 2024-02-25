package dev.patika.VetManagement.dao;

import dev.patika.VetManagement.entities.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccineRepo extends JpaRepository<Vaccine,Integer> {
}
