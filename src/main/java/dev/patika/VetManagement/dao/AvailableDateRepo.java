package dev.patika.VetManagement.dao;

import dev.patika.VetManagement.entities.AvailableDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface AvailableDateRepo extends JpaRepository<AvailableDate,Long> {
    Optional<AvailableDate> findByAvailableDateAndDoctorId(LocalDate availableDate,Long doctorId);
}
