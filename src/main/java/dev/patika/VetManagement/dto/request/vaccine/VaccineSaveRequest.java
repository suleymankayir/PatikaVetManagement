package dev.patika.VetManagement.dto.request.vaccine;

import dev.patika.VetManagement.entities.Animal;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VaccineSaveRequest {
    @NotNull(message = "Aşı ismi girilmesi zorunludur")
    private String name;
    @NotNull(message = "Aşı kodu girilmesi zorunludur")
    private String code;

    private LocalDate startDate;

    private LocalDate finishDate;

    private Long animalId;
}
