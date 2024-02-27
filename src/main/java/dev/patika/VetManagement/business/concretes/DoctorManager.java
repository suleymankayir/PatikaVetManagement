package dev.patika.VetManagement.business.concretes;

import dev.patika.VetManagement.business.abstracts.IDoctorService;
import dev.patika.VetManagement.core.exception.EntityAlreadyExistException;
import dev.patika.VetManagement.core.exception.NotFoundException;
import dev.patika.VetManagement.core.utilities.Msg;
import dev.patika.VetManagement.dao.DoctorRepo;
import dev.patika.VetManagement.entities.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DoctorManager implements IDoctorService {

    private final DoctorRepo doctorRepo;

    public DoctorManager(DoctorRepo doctorRepo) {
        this.doctorRepo = doctorRepo;
    }

    @Override
    public Doctor save(Doctor doctor) {
        Optional<Doctor> doctorFromDb = this.doctorRepo.findByMail(doctor.getMail());
        if (doctorFromDb.isPresent()){
            throw new EntityAlreadyExistException(doctorFromDb.get().getId(),Doctor.class);
        }
        return this.doctorRepo.save(doctor);
    }

    @Override
    public Doctor get(Long id) {
        return this.doctorRepo.findById(Math.toIntExact(id)).orElseThrow(()-> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Page<Doctor> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return this.doctorRepo.findAll(pageable);
    }

    @Override
    public Doctor update(Doctor doctor) {
        Optional<Doctor> doctorFromDb = this.doctorRepo.findByMail(doctor.getMail());
        if (doctorFromDb.isEmpty()){
            throw new NotFoundException("Bu bilgilere sahip bir doktor bulunmamaktadır");
        }
        this.get(doctor.getId());
        return this.doctorRepo.save(doctor);
    }

    @Override
    public boolean delete(Long id) {
        Doctor doctor = this.get(id);
        this.doctorRepo.delete(doctor);
        return true;
    }
}
