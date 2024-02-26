package dev.patika.VetManagement.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "availabledates")
public class AvailableDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "availabledate_id",columnDefinition = "serial")
    private Long id;

    @Column(name = "available_date",nullable = false)
    private LocalDate availableDate;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "doctor_available_date_id",referencedColumnName = "doctor_id")
    private Doctor doctor;
}
