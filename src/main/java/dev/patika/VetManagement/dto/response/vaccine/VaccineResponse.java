package dev.patika.VetManagement.dto.response.vaccine;

import dev.patika.VetManagement.entities.Animal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VaccineResponse {

    private Long id;

    private String name;

    private String code;

    private LocalDate startDate;

    private LocalDate finishDate;

    private Long animalId;
}
