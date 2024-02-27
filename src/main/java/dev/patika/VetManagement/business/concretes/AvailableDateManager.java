package dev.patika.VetManagement.business.concretes;

import dev.patika.VetManagement.business.abstracts.IAvailableDateService;
import dev.patika.VetManagement.core.exception.EntityAlreadyExistException;
import dev.patika.VetManagement.core.exception.NotFoundException;
import dev.patika.VetManagement.core.utilities.Msg;
import dev.patika.VetManagement.dao.AvailableDateRepo;
import dev.patika.VetManagement.entities.AvailableDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AvailableDateManager implements IAvailableDateService {

    private final AvailableDateRepo availableDateRepo;

    public AvailableDateManager(AvailableDateRepo availableDateRepo) {
        this.availableDateRepo = availableDateRepo;
    }

    @Override
    public AvailableDate save(AvailableDate availableDate) {
        Optional<AvailableDate> availableDateFromDb = this.availableDateRepo.findByAvailableDate(availableDate.getAvailableDate());
        if (availableDateFromDb.isPresent()){
            throw new EntityAlreadyExistException(availableDateFromDb.get().getId(), AvailableDate.class);
        }
        return this.availableDateRepo.save(availableDate);
    }

    @Override
    public AvailableDate get(Long id) {
        return this.availableDateRepo.findById(Math.toIntExact(id)).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Page<AvailableDate> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return this.availableDateRepo.findAll(pageable);
    }

    @Override
    public AvailableDate update(AvailableDate availableDate) {
        Optional<AvailableDate> availableDateFromDb = this.availableDateRepo.findByAvailableDate(availableDate.getAvailableDate());
        if (availableDateFromDb.isEmpty()){
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
}
