package dev.patika.VetManagement.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vaccines")
public class Vaccine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vaccine_id", columnDefinition = "serial")
    private Long id;
    @UniqueElements(message = "Aşı ismi eşsiz olmalıdır.")
    @Column(name = "vaccine_name")
    private String name;
    @UniqueElements(message = "Aşı kodu eşsiz olmalıdır.")
    @Column(name = "vaccine_code")
    private String code;

    @Column(name = "vaccine_protection_start_date")
    private LocalDate startDate;

    @Column(name = "vaccine_protection_finish_date")
    private LocalDate finishDate;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "vaccine_animal_id", referencedColumnName = "animal_id")
    private Animal animal;


}
