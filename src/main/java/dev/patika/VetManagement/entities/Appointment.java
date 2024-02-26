package dev.patika.VetManagement.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id",columnDefinition = "serial")
    private Long id;

    @Column(name = "appointment_date",nullable = false)
    private LocalDateTime appointmentDate;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "appointment_animal_id",referencedColumnName = "animal_id")
    private Animal animal;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "appointment_doctor_id", referencedColumnName = "doctor_id")
    private Doctor doctor;

}





