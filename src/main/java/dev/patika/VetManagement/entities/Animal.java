package dev.patika.VetManagement.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "animals")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "animal_id",columnDefinition = "serial")
    private Long id;

    @Column(name = "animal_name",nullable = false)
    private String name;

    @Column(name = "animal_species",nullable = false)
    private String species;

    @Column(name = "animal_breed",nullable = false)
    private String breed;

    @Column(name = "animal_gender",nullable = false)
    private String gender;

    @Column(name = "animal_color",nullable = false)
    private String color;

    @Column(name = "animal_date_of_birth")
    private LocalDate dateOfBirth;

    @JsonBackReference
    @OneToMany(mappedBy = "animal", cascade = CascadeType.REMOVE)
    private List<Vaccine> vaccineList;

    @JsonBackReference
    @OneToMany(mappedBy = "animal", cascade = CascadeType.REMOVE)
    private List<Appointment> appointmentList;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "animal_customer_id",referencedColumnName = "customer_id")
    private Customer customer;


}
