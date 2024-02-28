package dev.patika.VetManagement.dao;

import dev.patika.VetManagement.entities.Animal;
import dev.patika.VetManagement.entities.Appointment;
import dev.patika.VetManagement.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Long> {

    Optional<Appointment> findByDateTime(LocalDateTime appointmentDate);

    Optional<Appointment> findByDoctorAndDateTimeBetween(Doctor doctor, LocalDateTime startTime, LocalDateTime endTime);

    List<Appointment> findByDateTimeBetweenAndDoctor(LocalDateTime startTime, LocalDateTime endTime, Doctor doctor);

    List<Appointment> findByDateTimeBetweenAndAnimal(LocalDateTime startTime, LocalDateTime endTime, Animal animal);

    Optional<Appointment> findByDateTimeAndAnimalAndDoctor(LocalDateTime dateTime, Animal animal, Doctor doctor);
}
