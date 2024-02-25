package dev.patika.VetManagement.dto.request.appointment;

import dev.patika.VetManagement.entities.Animal;
import dev.patika.VetManagement.entities.Doctor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentSaveRequest {
    private LocalDate availableDate;

    private Long animalId;

    private Long doctorId;
}
