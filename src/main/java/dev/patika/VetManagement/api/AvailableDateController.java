package dev.patika.VetManagement.api;

import dev.patika.VetManagement.business.abstracts.IAvailableDateService;
import dev.patika.VetManagement.business.abstracts.IDoctorService;
import dev.patika.VetManagement.core.result.Result;
import dev.patika.VetManagement.core.result.ResultData;
import dev.patika.VetManagement.core.utilities.ResultHelper;
import dev.patika.VetManagement.dto.request.availableDate.AvailableDateSaveRequest;
import dev.patika.VetManagement.dto.request.availableDate.AvailableDateUpdateRequest;
import dev.patika.VetManagement.dto.response.CursorResponse;
import dev.patika.VetManagement.dto.response.availableDate.AvailableDateResponse;
import dev.patika.VetManagement.entities.AvailableDate;
import dev.patika.VetManagement.entities.Doctor;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/availabledates")
public class AvailableDateController {

    private final IAvailableDateService availableDateService;
    private final IDoctorService doctorService;

    // Constructor injecting necessary services for available date management
    public AvailableDateController(IAvailableDateService availableDateService, IDoctorService doctorService) {
        this.availableDateService = availableDateService;

        this.doctorService = doctorService;
    }

    // Değerlendirme Formu - 13
    // Endpoint to create a new available date
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AvailableDateResponse> save(@Valid @RequestBody AvailableDateSaveRequest availableDateSaveRequest) {
        Doctor doctor = this.doctorService.get(availableDateSaveRequest.getDoctorId());
        availableDateSaveRequest.setDoctorId(0L);

        AvailableDate saveDate = this.availableDateService.toAvailableDate(availableDateSaveRequest);
        saveDate.setDoctor(doctor);

        this.availableDateService.save(saveDate);
        return ResultHelper.created(this.availableDateService.toResponse(saveDate));


    }

    // Endpoint to retrieve an available date by ID
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AvailableDateResponse> get(@PathVariable("id") Long id) {
        AvailableDate availableDate = this.availableDateService.get(id);
        AvailableDateResponse availableDateResponse = this.availableDateService.toResponse(availableDate);
        return ResultHelper.success(availableDateResponse);
    }

    // Endpoint to retrieve a paginated list of available dates
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AvailableDateResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        Page<AvailableDate> availableDatePage = this.availableDateService.cursor(page, pageSize);
        Page<AvailableDateResponse> availableDateResponsePage = availableDatePage
                .map(this.availableDateService::toResponse);
        return ResultHelper.cursor(availableDateResponsePage);
    }

    // Endpoint to update an existing available date
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AvailableDateResponse> update(@Valid @RequestBody AvailableDateUpdateRequest availableDateUpdateRequest) {
        this.availableDateService.get(availableDateUpdateRequest.getId());
        AvailableDate date = this.availableDateService.toAvailableDate(availableDateUpdateRequest);
        this.availableDateService.update(date);
        return ResultHelper.success(this.availableDateService.toResponse(date));
    }

    // Endpoint to delete an available date by ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        this.availableDateService.delete(id);
        return ResultHelper.ok();
    }
}
