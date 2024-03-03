package dev.patika.VetManagement.api;

import dev.patika.VetManagement.business.abstracts.IDoctorService;
import dev.patika.VetManagement.core.result.Result;
import dev.patika.VetManagement.core.result.ResultData;
import dev.patika.VetManagement.core.utilities.ResultHelper;
import dev.patika.VetManagement.dto.request.doctor.DoctorSaveRequest;
import dev.patika.VetManagement.dto.request.doctor.DoctorUpdateRequest;
import dev.patika.VetManagement.dto.response.CursorResponse;
import dev.patika.VetManagement.dto.response.doctor.DoctorResponse;
import dev.patika.VetManagement.entities.Doctor;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/doctors")
public class DoctorController {


    private final IDoctorService doctorService;

    // Constructor injecting necessary services for doctor management
    public DoctorController(IDoctorService doctorService) {
        this.doctorService = doctorService;

    }

    // Değerlendirme Formu - 12
    // Endpoint to create a new doctor
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<DoctorResponse> save(@Valid @RequestBody DoctorSaveRequest doctorSaveRequest) {
        Doctor saveDoctor = this.doctorService.toDoctor(doctorSaveRequest);
        this.doctorService.save(saveDoctor);
        return ResultHelper.created(this.doctorService.toResponse(saveDoctor));
    }

    // Endpoint to retrieve a paginated list of doctors
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<DoctorResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        Page<Doctor> doctorPage = this.doctorService.cursor(page, pageSize);
        Page<DoctorResponse> doctorResponsePage = doctorPage
                .map(this.doctorService::toResponse);
        return ResultHelper.cursor(doctorResponsePage);

    }

    // Endpoint to retrieve a doctor by ID
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<DoctorResponse> get(@PathVariable("id") Long id) {
        Doctor doctor = this.doctorService.get(id);
        DoctorResponse doctorResponse = this.doctorService.toResponse(doctor);
        return ResultHelper.success(doctorResponse);
    }

    // Endpoint to update an existing doctor
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<DoctorResponse> update(@Valid @RequestBody DoctorUpdateRequest doctorUpdateRequest) {
        this.doctorService.get(doctorUpdateRequest.getId());
        Doctor updateDoctor = this.doctorService.toDoctor(doctorUpdateRequest);
        this.doctorService.update(updateDoctor);
        return ResultHelper.success(this.doctorService.toResponse(updateDoctor));

    }

    // Endpoint to delete a doctor by ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        this.doctorService.delete(id);
        return ResultHelper.ok();
    }

}
