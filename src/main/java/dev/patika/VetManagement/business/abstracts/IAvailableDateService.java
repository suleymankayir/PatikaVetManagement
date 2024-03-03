package dev.patika.VetManagement.business.abstracts;

import dev.patika.VetManagement.dto.request.availableDate.AvailableDateSaveRequest;
import dev.patika.VetManagement.dto.request.availableDate.AvailableDateUpdateRequest;
import dev.patika.VetManagement.dto.response.availableDate.AvailableDateResponse;
import dev.patika.VetManagement.entities.AvailableDate;
import org.springframework.data.domain.Page;

public interface IAvailableDateService {
    AvailableDate save(AvailableDate availableDate);

    AvailableDate get(Long id);

    Page<AvailableDate> cursor(int page, int pageSize);

    AvailableDate update(AvailableDate availableDate);

    boolean delete(Long id);

    AvailableDate toAvailableDate(AvailableDateSaveRequest availableDateSaveRequest);

    AvailableDateResponse toResponse(AvailableDate availableDate);

    AvailableDate toAvailableDate(AvailableDateUpdateRequest availableDateUpdateRequest);
}
