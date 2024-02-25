package dev.patika.VetManagement.api;

import dev.patika.VetManagement.business.abstracts.IAvailableDateService;
import dev.patika.VetManagement.business.abstracts.IDoctorService;
import dev.patika.VetManagement.core.config.modelMapper.IModelMapperService;
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
    private final IModelMapperService modelMapper;
    private final IDoctorService doctorService;


    public AvailableDateController(IAvailableDateService availableDateService, IModelMapperService modelMapper, IDoctorService doctorService) {
        this.availableDateService = availableDateService;
        this.modelMapper = modelMapper;
        this.doctorService = doctorService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AvailableDateResponse> save(@Valid @RequestBody AvailableDateSaveRequest availableDateSaveRequest) {
        Doctor doctor = this.doctorService.get(availableDateSaveRequest.getDoctorId());
        availableDateSaveRequest.setDoctorId(0L);

        AvailableDate saveDate = this.modelMapper.forRequest().map(availableDateSaveRequest, AvailableDate.class);
        saveDate.setDoctor(doctor);

        this.availableDateService.save(saveDate);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveDate, AvailableDateResponse.class));


    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AvailableDateResponse> get(@PathVariable("id") Long id) {
        AvailableDate availableDate = this.availableDateService.get(id);
        AvailableDateResponse availableDateResponse = this.modelMapper.forResponse().map(availableDate, AvailableDateResponse.class);
        return ResultHelper.success(availableDateResponse);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AvailableDateResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        Page<AvailableDate> availableDatePage = this.availableDateService.cursor(page, pageSize);
        Page<AvailableDateResponse> availableDateResponsePage = availableDatePage
                .map(availableDate -> this.modelMapper.forResponse().map(availableDate, AvailableDateResponse.class));
        return ResultHelper.cursor(availableDateResponsePage);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AvailableDateResponse> update(@Valid @RequestBody AvailableDateUpdateRequest availableDateUpdateRequest) {
        this.availableDateService.get(availableDateUpdateRequest.getId());
        AvailableDate date = this.modelMapper.forRequest().map(availableDateUpdateRequest, AvailableDate.class);
        this.availableDateService.update(date);
        return ResultHelper.success(this.modelMapper.forResponse().map(date, AvailableDateResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        this.availableDateService.delete(id);
        return ResultHelper.ok();
    }
}
