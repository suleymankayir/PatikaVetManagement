package dev.patika.VetManagement.dto.request.animal;

import dev.patika.VetManagement.entities.Appointment;
import dev.patika.VetManagement.entities.Customer;
import dev.patika.VetManagement.entities.Vaccine;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalUpdateRequest {
    @Positive
    private Long id;

    private String name;

    private String species;

    private String breed;

    private String gender;

    private String color;

    private LocalDate dateOfBirth;

    private Long customerId;
}
