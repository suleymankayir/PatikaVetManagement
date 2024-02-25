package dev.patika.VetManagement.business.abstracts;

import dev.patika.VetManagement.entities.AvailableDate;
import dev.patika.VetManagement.entities.Customer;
import org.springframework.data.domain.Page;

public interface IAvailableDateService {
    AvailableDate save(AvailableDate availableDate);
    AvailableDate get(Long id);
    Page<AvailableDate> cursor(int page, int pageSize);
    AvailableDate update(AvailableDate availableDate);
    boolean delete(Long id);
}
