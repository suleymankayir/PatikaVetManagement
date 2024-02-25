package dev.patika.VetManagement.business.abstracts;

import dev.patika.VetManagement.entities.Vaccine;
import org.springframework.data.domain.Page;

public interface IVaccineService {
    Vaccine save(Vaccine vaccine);
    Vaccine get(Long id);
    Page<Vaccine> cursor(int page, int pageSize);
    Vaccine update(Vaccine vaccine);
    boolean delete(Long id);
}
