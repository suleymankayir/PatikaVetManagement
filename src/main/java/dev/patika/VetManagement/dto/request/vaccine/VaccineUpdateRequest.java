package dev.patika.VetManagement.dto.request.vaccine;

import dev.patika.VetManagement.entities.Animal;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VaccineUpdateRequest {
    @NotNull
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String code;

    private LocalDate startDate;

    private LocalDate finishDate;

    private Long animalId;
}
