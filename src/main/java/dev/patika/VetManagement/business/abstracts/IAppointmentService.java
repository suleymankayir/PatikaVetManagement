package dev.patika.VetManagement.business.abstracts;

import dev.patika.VetManagement.entities.Appointment;
import dev.patika.VetManagement.entities.AvailableDate;
import org.springframework.data.domain.Page;

public interface IAppointmentService {
    Appointment save(Appointment appointment);
    Appointment get(Long id);
    Page<Appointment> cursor(int page, int pageSize);
    Appointment update(Appointment appointment);
    boolean delete(Long id);
}
