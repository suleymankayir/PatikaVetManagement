package dev.patika.VetManagement.dto.request.appointment;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import dev.patika.VetManagement.core.utilities.LocalDateTimeDeserializer;
import dev.patika.VetManagement.entities.Animal;
import dev.patika.VetManagement.entities.Doctor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentSaveRequest {
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime appointmentTime;

    private Long animalId;

    private Long doctorId;
}
