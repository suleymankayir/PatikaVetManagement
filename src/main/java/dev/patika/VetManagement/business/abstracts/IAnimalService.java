package dev.patika.VetManagement.business.abstracts;

import dev.patika.VetManagement.dto.request.animal.AnimalSaveRequest;
import dev.patika.VetManagement.dto.request.animal.AnimalUpdateRequest;
import dev.patika.VetManagement.dto.response.animal.AnimalResponse;
import dev.patika.VetManagement.entities.Animal;
import org.springframework.data.domain.Page;

public interface IAnimalService {
    Animal save(Animal animal);

    Animal get(Long id);

    Page<Animal> cursor(int page, int pageSize);

    Animal update(Animal animal);

    boolean delete(Long id);

    Animal findByAnimalName(String name);

    Animal toAnimal(AnimalSaveRequest animalSaveRequest);
    AnimalResponse toResponse(Animal animal);
    Animal toAnimal(AnimalUpdateRequest animalUpdateRequest);


}
