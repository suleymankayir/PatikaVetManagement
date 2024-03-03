package dev.patika.VetManagement.business.concretes;

import dev.patika.VetManagement.business.abstracts.IAvailableDateService;
import dev.patika.VetManagement.core.config.modelMapper.IModelMapperService;
import dev.patika.VetManagement.core.exception.EntityAlreadyExistException;
import dev.patika.VetManagement.core.exception.NotFoundException;
import dev.patika.VetManagement.core.utilities.Msg;
import dev.patika.VetManagement.dao.AvailableDateRepo;
import dev.patika.VetManagement.dto.request.availableDate.AvailableDateSaveRequest;
import dev.patika.VetManagement.dto.request.availableDate.AvailableDateUpdateRequest;
import dev.patika.VetManagement.dto.response.availableDate.AvailableDateResponse;
import dev.patika.VetManagement.entities.AvailableDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AvailableDateManager implements IAvailableDateService {

    private final AvailableDateRepo availableDateRepo;
    private final IModelMapperService modelMapper;

    public AvailableDateManager(AvailableDateRepo availableDateRepo, IModelMapperService modelMapper) {
        this.availableDateRepo = availableDateRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public AvailableDate save(AvailableDate availableDate) {
        Optional<AvailableDate> availableDateFromDb = this.availableDateRepo.findByAvailableDateAndDoctorId(availableDate.getAvailableDate(), availableDate.getDoctor().getId());
        if (availableDateFromDb.isPresent()) {
            throw new EntityAlreadyExistException(availableDateFromDb.get().getId(), AvailableDate.class);
        }
        return this.availableDateRepo.save(availableDate);
    }

    @Override
    public AvailableDate get(Long id) {
        return this.availableDateRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Page<AvailableDate> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.availableDateRepo.findAll(pageable);
    }

    @Override
    public AvailableDate update(AvailableDate availableDate) {
        Optional<AvailableDate> availableDateFromDb = this.availableDateRepo.findByAvailableDateAndDoctorId(availableDate.getAvailableDate(), availableDate.getDoctor().getId());
        if (availableDateFromDb.isEmpty()) {
            throw new NotFoundException("Bu bilgilere sahip bir gün bilgisi bulunmamaktadır.");
        }
        this.get(availableDate.getId());
        return this.availableDateRepo.save(availableDate);
    }

    @Override
    public boolean delete(Long id) {
        AvailableDate availableDate = this.get(id);
        this.availableDateRepo.delete(availableDate);
        return true;
    }

    @Override
    public AvailableDate toAvailableDate(AvailableDateSaveRequest availableDateSaveRequest) {
        return this.modelMapper.forRequest().map(availableDateSaveRequest, AvailableDate.class);
    }

    @Override
    public AvailableDateResponse toResponse(AvailableDate availableDate) {
        return this.modelMapper.forResponse().map(availableDate, AvailableDateResponse.class);
    }

    @Override
    public AvailableDate toAvailableDate(AvailableDateUpdateRequest availableDateUpdateRequest) {
        return this.modelMapper.forRequest().map(availableDateUpdateRequest, AvailableDate.class);
    }
}
