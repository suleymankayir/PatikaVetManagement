package dev.patika.VetManagement.business.abstracts;

import dev.patika.VetManagement.dto.request.appointment.AppointmentSaveRequest;
import dev.patika.VetManagement.dto.request.appointment.AppointmentUpdateRequest;
import dev.patika.VetManagement.dto.response.appointment.AppointmentResponse;
import dev.patika.VetManagement.entities.Appointment;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

public interface IAppointmentService {
    Appointment save(Appointment appointment);

    Appointment get(Long id);

    Page<Appointment> cursor(int page, int pageSize);

    Appointment update(Appointment appointment);

    boolean delete(Long id);

    List<Appointment> getAppointmentsByDoctor(Long id, LocalDateTime start, LocalDateTime end);

    List<Appointment> getAppointmentsByAnimal(Long id, LocalDateTime start, LocalDateTime end);

    Appointment toAppointment(AppointmentSaveRequest appointmentSaveRequest);

    AppointmentResponse toResponse(Appointment appointment);

    Appointment toAppointment(AppointmentUpdateRequest appointmentUpdateRequest);
}
