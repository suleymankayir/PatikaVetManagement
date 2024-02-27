package dev.patika.VetManagement.dao;

import dev.patika.VetManagement.entities.Animal;
import dev.patika.VetManagement.entities.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface VaccineRepo extends JpaRepository<Vaccine,Integer> {
    boolean existsByNameAndCodeAndAnimalAndFinishDateAfter(String name, String code,Animal animal, LocalDate date);

    List<Vaccine> findByFinishDateBetween(LocalDate startDate, LocalDate finishDate);

    Optional<Vaccine> findByNameAndCodeAndStartDateAndFinishDate(String name, String code, LocalDate startDate, LocalDate finishDate);
}
