package dev.patika.VetManagement.dto.request.appointment;

import dev.patika.VetManagement.entities.Animal;
import dev.patika.VetManagement.entities.Doctor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentUpdateRequest {

    private Long id;

    private LocalDate dateTime;

    private Long animalId;

    private Long doctorId;
}
