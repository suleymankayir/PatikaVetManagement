package dev.patika.VetManagement.business.concretes;

import dev.patika.VetManagement.business.abstracts.IAppointmentService;
import dev.patika.VetManagement.core.exception.AppointmentExistException;
import dev.patika.VetManagement.core.exception.EntityAlreadyExistException;
import dev.patika.VetManagement.core.exception.NotFoundException;
import dev.patika.VetManagement.core.utilities.Msg;
import dev.patika.VetManagement.dao.AnimalRepo;
import dev.patika.VetManagement.dao.AppointmentRepo;
import dev.patika.VetManagement.dao.DoctorRepo;
import dev.patika.VetManagement.entities.Animal;
import dev.patika.VetManagement.entities.Appointment;
import dev.patika.VetManagement.entities.AvailableDate;
import dev.patika.VetManagement.entities.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentManager implements IAppointmentService {

    private final AppointmentRepo appointmentRepo;
    private final DoctorRepo doctorRepo;

    private final AnimalRepo animalRepo;

    public AppointmentManager(AppointmentRepo appointmentRepo, DoctorRepo doctorRepo, AnimalRepo animalRepo) {
        this.appointmentRepo = appointmentRepo;
        this.doctorRepo = doctorRepo;
        this.animalRepo = animalRepo;
    }

    @Override
    public Appointment save(Appointment appointment) {
        Optional<Appointment> appointmentFromDb = this.appointmentRepo.findByDateTime(appointment.getDateTime());
        if (appointmentFromDb.isPresent()) {
            throw new EntityAlreadyExistException(appointmentFromDb.get().getId(), Appointment.class);
        }

        List<LocalDate> availableDates = appointment.getDoctor().getAvailableDateList().stream()
                .map(AvailableDate::getAvailableDate)
                .collect(Collectors.toList());


        LocalDate appointmentDate = appointment.getDateTime().toLocalDate();
        if (availableDates.contains(appointmentDate)) {
            LocalDateTime endDateTime = appointment.getDateTime().plusHours(1);
            if (appointment.getDateTime().getMinute() == 0 && appointment.getDateTime().getSecond() == 0) {
                Optional<Appointment> existingAppointment = this.appointmentRepo.findByDoctorAndDateTimeBetween(appointment.getDoctor(), appointment.getDateTime(), endDateTime);

                if (existingAppointment.isPresent()) {
                    throw new AppointmentExistException("Bu saat için uygun randevu bulunmamaktadır");
                } else {
                    return this.appointmentRepo.save(appointment);
                }

            } else {
                throw new AppointmentExistException("Sadece tam saatlerde randevu alınabilir.");
            }

        } else {
            throw new NotFoundException("Doktor bu tarihte çalışmamaktadır");
        }


    }

    @Override
    public Appointment get(Long id) {
        return this.appointmentRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Page<Appointment> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.appointmentRepo.findAll(pageable);
    }

    @Override
    public Appointment update(Appointment appointment) {
        Optional<Appointment> appointmentFromDb = this.appointmentRepo.findByDateTime(appointment.getDateTime());
        if (appointmentFromDb.isEmpty()) {
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

    @Override
    public List<Appointment> getAppointmentsByDoctor(Long id, LocalDateTime startDate, LocalDateTime endDate) {
        Optional<Doctor> doctor = this.doctorRepo.findById(id);

        return this.appointmentRepo.findByDateTimeBetweenAndDoctor(startDate, endDate, doctor.get());
    }

    @Override
    public List<Appointment> getAppointmentsByAnimal(Long id, LocalDateTime start, LocalDateTime end) {
        Optional<Animal> animal = this.animalRepo.findById(id);

        return this.appointmentRepo.findByDateTimeBetweenAndAnimal(start, end, animal.get());


    }


}


