package dev.patika.VetManagement.business.abstracts;

import dev.patika.VetManagement.entities.Animal;
import dev.patika.VetManagement.entities.Appointment;
import org.springframework.data.domain.Page;

public interface IAnimalService {
    Animal save(Animal animal);
    Animal get(Long id);
    Page<Animal> cursor(int page, int pageSize);
    Animal update(Animal animal);
    boolean delete(Long id);
}
