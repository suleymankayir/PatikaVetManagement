package dev.patika.VetManagement.business.abstracts;

import dev.patika.VetManagement.dto.request.doctor.DoctorSaveRequest;
import dev.patika.VetManagement.dto.request.doctor.DoctorUpdateRequest;
import dev.patika.VetManagement.dto.response.doctor.DoctorResponse;
import dev.patika.VetManagement.entities.Doctor;
import org.springframework.data.domain.Page;

public interface IDoctorService {
    Doctor save(Doctor doctor);

    Doctor get(Long id);

    Page<Doctor> cursor(int page, int pageSize);

    Doctor update(Doctor doctor);

    boolean delete(Long id);

    Doctor toDoctor(DoctorSaveRequest doctorSaveRequest);

    DoctorResponse toResponse(Doctor doctor);

    Doctor toDoctor(DoctorUpdateRequest doctorUpdateRequest);
}
