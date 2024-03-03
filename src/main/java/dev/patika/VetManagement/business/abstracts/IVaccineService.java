package dev.patika.VetManagement.business.abstracts;

import dev.patika.VetManagement.dto.request.vaccine.VaccineSaveRequest;
import dev.patika.VetManagement.dto.request.vaccine.VaccineUpdateRequest;
import dev.patika.VetManagement.dto.response.vaccine.VaccineResponse;
import dev.patika.VetManagement.entities.Vaccine;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface IVaccineService {
    Vaccine save(Vaccine vaccine);

    Vaccine get(Long id);

    Page<Vaccine> cursor(int page, int pageSize);

    Vaccine update(Vaccine vaccine);

    boolean delete(Long id);

    List<Vaccine> getVaccinesByDate(LocalDate startDate, LocalDate finishDate);

    Vaccine toVaccine(VaccineSaveRequest vaccineSaveRequest);

    VaccineResponse toResponse(Vaccine vaccine);

    Vaccine toVaccine(VaccineUpdateRequest vaccineUpdateRequest);


}
