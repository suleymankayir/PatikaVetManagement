
package dev.patika.VetManagement.business.concretes;

import dev.patika.VetManagement.business.abstracts.IVaccineService;
import dev.patika.VetManagement.core.config.modelMapper.IModelMapperService;
import dev.patika.VetManagement.core.exception.EntityAlreadyExistException;
import dev.patika.VetManagement.core.exception.NotFoundException;
import dev.patika.VetManagement.core.utilities.Msg;
import dev.patika.VetManagement.dao.VaccineRepo;
import dev.patika.VetManagement.dto.request.vaccine.VaccineSaveRequest;
import dev.patika.VetManagement.dto.request.vaccine.VaccineUpdateRequest;
import dev.patika.VetManagement.dto.response.vaccine.VaccineResponse;
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
    private final IModelMapperService modelMapper;

    public VaccineManager(VaccineRepo vaccineRepo, IModelMapperService modelMapper) {
        this.vaccineRepo = vaccineRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public Vaccine save(Vaccine vaccine) {
        Optional<Vaccine> vaccineFromDb = this.vaccineRepo.findByNameAndCodeAndStartDateAndFinishDateAndAnimal(vaccine.getName(), vaccine.getCode(), vaccine.getStartDate(), vaccine.getFinishDate(), vaccine.getAnimal());
        if (vaccineFromDb.isPresent()) {
            throw new EntityAlreadyExistException(vaccineFromDb.get().getId(), Vaccine.class);
        }
        // Değerlendirme Formu - 19
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

    @Override
    public Vaccine toVaccine(VaccineSaveRequest vaccineSaveRequest) {
        return this.modelMapper.forRequest().map(vaccineSaveRequest, Vaccine.class);
    }

    @Override
    public VaccineResponse toResponse(Vaccine vaccine) {
        return this.modelMapper.forResponse().map(vaccine, VaccineResponse.class);
    }

    @Override
    public Vaccine toVaccine(VaccineUpdateRequest vaccineUpdateRequest) {
        return this.modelMapper.forRequest().map(vaccineUpdateRequest, Vaccine.class);
    }


}
