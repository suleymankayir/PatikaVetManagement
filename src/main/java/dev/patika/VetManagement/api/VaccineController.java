package dev.patika.VetManagement.api;

import dev.patika.VetManagement.business.abstracts.IAnimalService;
import dev.patika.VetManagement.business.abstracts.IVaccineService;
import dev.patika.VetManagement.core.result.Result;
import dev.patika.VetManagement.core.result.ResultData;
import dev.patika.VetManagement.core.utilities.ResultHelper;
import dev.patika.VetManagement.dto.request.vaccine.VaccineSaveRequest;
import dev.patika.VetManagement.dto.request.vaccine.VaccineUpdateRequest;
import dev.patika.VetManagement.dto.response.CursorResponse;
import dev.patika.VetManagement.dto.response.vaccine.VaccineResponse;
import dev.patika.VetManagement.entities.Animal;
import dev.patika.VetManagement.entities.Vaccine;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/vaccines")
public class VaccineController {

    private final IVaccineService vaccineService;

    private final IAnimalService animalService;

    // Constructor injecting necessary services for vaccine management
    public VaccineController(IVaccineService vaccineService, IAnimalService animalService) {
        this.vaccineService = vaccineService;
        this.animalService = animalService;
    }

    // Değerlendirme Formu - 15
    // Endpoint to create a new vaccine
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<VaccineResponse> save(@Valid @RequestBody VaccineSaveRequest vaccineSaveRequest) {
        Animal animal = this.animalService.get(vaccineSaveRequest.getAnimalId());
        vaccineSaveRequest.setAnimalId(0L);

        Vaccine saveVaccine = this.vaccineService.toVaccine(vaccineSaveRequest);
        saveVaccine.setAnimal(animal);

        this.vaccineService.save(saveVaccine);
        return ResultHelper.created(this.vaccineService.toResponse(saveVaccine));
    }

    // Endpoint to retrieve a vaccine by ID
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<VaccineResponse> get(@PathVariable("id") Long id) {
        Vaccine vaccine = this.vaccineService.get(id);
        VaccineResponse vaccineResponse = this.vaccineService.toResponse(vaccine);
        return ResultHelper.success(vaccineResponse);
    }

    // Endpoint to retrieve a paginated list of vaccines
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<VaccineResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        Page<Vaccine> vaccinePage = this.vaccineService.cursor(page, pageSize);
        Page<VaccineResponse> vaccineResponsePage = vaccinePage
                .map(this.vaccineService::toResponse);
        return ResultHelper.cursor(vaccineResponsePage);
    }

    // Endpoint to update an existing vaccine
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<VaccineResponse> update(@Valid @RequestBody VaccineUpdateRequest vaccineUpdateRequest) {
        this.vaccineService.get(vaccineUpdateRequest.getId());
        Vaccine updateVaccine = this.vaccineService.toVaccine(vaccineUpdateRequest);
        this.vaccineService.update(updateVaccine);
        return ResultHelper.success(this.vaccineService.toResponse(updateVaccine));
    }

    // Endpoint to delete a vaccine by ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        this.vaccineService.delete(id);
        return ResultHelper.ok();
    }

    // Değerlendirme Formu - 20
    // Endpoint to retrieve all vaccines of an animal by animal ID
    @GetMapping("/{id}/animalvaccines")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<VaccineResponse>> getAnimals(@PathVariable("id") Long id) {

        Animal animal = animalService.get(id);
        List<Vaccine> vaccineList = animal.getVaccineList();
        List<VaccineResponse> vaccineResponseList = new ArrayList<>();

        for (Vaccine vaccine : vaccineList) {
            vaccineResponseList.add(this.vaccineService.toResponse(vaccine));
        }

        return ResultHelper.success(vaccineResponseList);
    }

    // Değerlendirme Formu - 21
    // Endpoint to retrieve all vaccines within a date range
    @GetMapping("/vaccineByDate")
    public List<Vaccine> getAnimalsByVaccine(
            @RequestParam(name = "startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(name = "finishDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate finishDate
    ) {
        return this.vaccineService.getVaccinesByDate(startDate, finishDate);
    }


}

