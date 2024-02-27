package dev.patika.VetManagement.business.concretes;

import dev.patika.VetManagement.business.abstracts.IAppointmentService;
import dev.patika.VetManagement.core.exception.EntityAlreadyExistException;
import dev.patika.VetManagement.core.exception.NotFoundException;
import dev.patika.VetManagement.core.utilities.Msg;
import dev.patika.VetManagement.dao.AppointmentRepo;
import dev.patika.VetManagement.entities.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppointmentManager implements IAppointmentService {

    private final AppointmentRepo appointmentRepo;

    public AppointmentManager(AppointmentRepo appointmentRepo) {
        this.appointmentRepo = appointmentRepo;
    }

    @Override
    public Appointment save(Appointment appointment) {
        Optional<Appointment> appointmentFromDb = this.appointmentRepo.findByAppointmentDate(appointment.getAppointmentDate());
        if (appointmentFromDb.isPresent()) {
            throw new EntityAlreadyExistException(appointmentFromDb.get().getId(),Appointment.class);
        }

        return this.appointmentRepo.save(appointment);
    }

    @Override
    public Appointment get(Long id) {
        return this.appointmentRepo.findById(Math.toIntExact(id)).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Page<Appointment> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.appointmentRepo.findAll(pageable);
    }

    @Override
    public Appointment update(Appointment appointment) {
        Optional<Appointment> appointmentFromDb = this.appointmentRepo.findByAppointmentDate(appointment.getAppointmentDate());
        if (appointmentFromDb.isEmpty()){
            throw new NotFoundException("Bu bilgilere sahip bir randevu bulunamadı");
        }
        this.get(appointment.getId());
        return this.appointmentRepo.save(appointment);
    }

    @Override
    public boolean delete(Long id) {
        Appointment appointment = this.get(id);
        this.appointmentRepo.delete(appointment);
        return true;
    }
}
