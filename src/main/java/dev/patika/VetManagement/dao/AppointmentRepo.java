package dev.patika.VetManagement.dao;

import dev.patika.VetManagement.entities.Appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment,Integer> {


}
