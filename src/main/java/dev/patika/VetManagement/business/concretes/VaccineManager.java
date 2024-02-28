
package dev.patika.VetManagement.business.concretes;

import dev.patika.VetManagement.business.abstracts.IVaccineService;
import dev.patika.VetManagement.core.exception.EntityAlreadyExistException;
import dev.patika.VetManagement.core.exception.NotFoundException;
import dev.patika.VetManagement.core.utilities.Msg;
import dev.patika.VetManagement.dao.VaccineRepo;
import dev.patika.VetManagement.entities.Vaccine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class VaccineManager implements IVaccineService {

    private final VaccineRepo vaccineRepo;

    public VaccineManager(VaccineRepo vaccineRepo) {
        this.vaccineRepo = vaccineRepo;
    }

    @Override
    public Vaccine save(Vaccine vaccine) {
        Optional<Vaccine> vaccineFromDb = this.vaccineRepo.findByNameAndCodeAndStartDateAndFinishDate(vaccine.getName(), vaccine.getCode(), vaccine.getStartDate(), vaccine.getFinishDate());
        if (vaccineFromDb.isPresent()) {
            throw new EntityAlreadyExistException(vaccineFromDb.get().getId(), Vaccine.class);
        }

        LocalDate date = vaccine.getStartDate();
        if (!vaccineRepo.existsByNameAndCodeAndAnimalAndFinishDateAfter(vaccine.getName(), vaccine.getCode(), vaccine.getAnimal(), date)) {
            return this.vaccineRepo.save(vaccine);
        } else {
            throw new NotFoundException("Aşı koruyuculuk tarihi henüz bitmemiş");
        }

    }

    @Override
    public Vaccine get(Long id) {
        return this.vaccineRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Page<Vaccine> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.vaccineRepo.findAll(pageable);
    }

    @Override
    public Vaccine update(Vaccine vaccine) {
        this.get(vaccine.getId());
        return this.vaccineRepo.save(vaccine);
    }

    @Override
    public boolean delete(Long id) {
        Vaccine vaccine = this.get(id);
        this.vaccineRepo.delete(vaccine);
        return true;
    }

    @Override
    public List<Vaccine> getVaccinesByDate(LocalDate startDate, LocalDate finishDate) {
        return this.vaccineRepo.findByFinishDateBetween(startDate, finishDate);
    }


}
