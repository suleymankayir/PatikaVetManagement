package dev.patika.VetManagement.dto.request.availableDate;

import dev.patika.VetManagement.entities.Doctor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailableDateSaveRequest {

    private LocalDate availableDate;

    private Long doctorId;
}
