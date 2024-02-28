package dev.patika.VetManagement.dto.response.appointment;

import dev.patika.VetManagement.entities.Animal;
import dev.patika.VetManagement.entities.Doctor;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentResponse {

    private Long id;

    private LocalDateTime dateTime;

    private Long animalId;

    private Long doctorId;
}
