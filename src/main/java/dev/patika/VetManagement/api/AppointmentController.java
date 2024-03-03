package dev.patika.VetManagement.api;

import dev.patika.VetManagement.business.abstracts.IAnimalService;
import dev.patika.VetManagement.business.abstracts.IAppointmentService;
import dev.patika.VetManagement.business.abstracts.IDoctorService;
import dev.patika.VetManagement.core.result.Result;
import dev.patika.VetManagement.core.result.ResultData;
import dev.patika.VetManagement.core.utilities.ResultHelper;
import dev.patika.VetManagement.dto.request.appointment.AppointmentSaveRequest;
import dev.patika.VetManagement.dto.request.appointment.AppointmentUpdateRequest;
import dev.patika.VetManagement.dto.response.CursorResponse;
import dev.patika.VetManagement.dto.response.appointment.AppointmentResponse;
import dev.patika.VetManagement.entities.Animal;
import dev.patika.VetManagement.entities.Appointment;
import dev.patika.VetManagement.entities.Doctor;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/appointments")
public class AppointmentController {

    private final IAppointmentService appointmentService;
    private final IAnimalService animalService;
    private final IDoctorService doctorService;

    // Constructor injecting necessary services for appointment management
    public AppointmentController(IAppointmentService appointmentService, IAnimalService animalService, IDoctorService doctorService) {
        this.appointmentService = appointmentService;
        this.animalService = animalService;
        this.doctorService = doctorService;
    }

    // Endpoint to create a new appointment
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AppointmentResponse> save(@Valid @RequestBody AppointmentSaveRequest appointmentSaveRequest) {

        Animal animal = this.animalService.get(appointmentSaveRequest.getAnimalId());
        appointmentSaveRequest.setAnimalId(0L);

        Doctor doctor = this.doctorService.get(appointmentSaveRequest.getDoctorId());
        appointmentSaveRequest.setDoctorId(0L);

        Appointment saveAppointment = this.appointmentService.toAppointment(appointmentSaveRequest);
        saveAppointment.setAnimal(animal);
        saveAppointment.setDoctor(doctor);
        this.appointmentService.save(saveAppointment);
        return ResultHelper.created(this.appointmentService.toResponse(saveAppointment));
    }

    // Endpoint to retrieve appointments with pagination
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AppointmentResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        Page<Appointment> appointmentPage = this.appointmentService.cursor(page, pageSize);
        Page<AppointmentResponse> appointmentResponsePage = appointmentPage
                .map(this.appointmentService::toResponse);
        return ResultHelper.cursor(appointmentResponsePage);
    }

    // Endpoint to retrieve a specific appointment by ID
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AppointmentResponse> get(@PathVariable("id") Long id) {
        Appointment appointment = this.appointmentService.get(id);
        AppointmentResponse appointmentResponse = this.appointmentService.toResponse(appointment);
        return ResultHelper.success(appointmentResponse);
    }

    // Endpoint to update an existing appointment
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AppointmentResponse> update(@Valid @RequestBody AppointmentUpdateRequest appointmentUpdateRequest) {
        this.appointmentService.get(appointmentUpdateRequest.getId());
        Appointment updateAppointment = this.appointmentService.toAppointment(appointmentUpdateRequest);
        this.appointmentService.update(updateAppointment);
        return ResultHelper.success(this.appointmentService.toResponse(updateAppointment));
    }

    // Endpoint to retrieve appointments for a specific doctor within a date range
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        this.appointmentService.delete(id);
        return ResultHelper.ok();
    }

    // Değerlendirme Formu - 24
    // Endpoint to retrieve appointments for a specific doctor within a date range
    @GetMapping("/appointmentsByDoctor")
    @ResponseStatus(HttpStatus.OK)
    public List<Appointment> getAppointmentsByDoctor(
            @RequestParam(name = "doctorId") Long doctorId,
            @RequestParam(name = "startDate") LocalDate startDate,
            @RequestParam(name = "endDate") LocalDate endDate
    ) {
        return this.appointmentService.getAppointmentsByDoctor(doctorId, startDate.atStartOfDay(), endDate.atStartOfDay());

    }

    // Değerlendirme Formu - 23
    // Endpoint to retrieve appointments for a specific animal within a date range
    @GetMapping("/appointmentsByAnimal")
    @ResponseStatus(HttpStatus.OK)
    public List<Appointment> getAppointmentsByAnimal(
            @RequestParam(name = "animalId") Long animalId,
            @RequestParam(name = "startDate") LocalDate startDate,
            @RequestParam(name = "endDate") LocalDate endDate
    ) {
        return this.appointmentService.getAppointmentsByAnimal(animalId, startDate.atStartOfDay(), endDate.atStartOfDay());

    }

}
