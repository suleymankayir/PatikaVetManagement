package dev.patika.VetManagement.business.concretes;

import dev.patika.VetManagement.business.abstracts.IAnimalService;
import dev.patika.VetManagement.core.exception.EntityAlreadyExistException;
import dev.patika.VetManagement.core.exception.NoExistanceException;
import dev.patika.VetManagement.core.exception.NotFoundException;
import dev.patika.VetManagement.core.utilities.Msg;
import dev.patika.VetManagement.dao.AnimalRepo;
import dev.patika.VetManagement.entities.Animal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnimalManager implements IAnimalService {

    private final AnimalRepo animalRepo;

    public AnimalManager(AnimalRepo animalRepo) {
        this.animalRepo = animalRepo;
    }

    @Override
    public Animal save(Animal animal) {
        Optional<Animal> animalFromDb = animalRepo.findByNameAndSpeciesAndBreedAndGenderAndColor(animal.getName(), animal.getSpecies(), animal.getBreed(), animal.getGender(), animal.getColor());
        if (animalFromDb.isPresent()) {
            throw new EntityAlreadyExistException(animalFromDb.get().getId(), Animal.class);
        }
        return this.animalRepo.save(animal);
    }

    @Override
    public Animal get(Long id) {
        return this.animalRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Page<Animal> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.animalRepo.findAll(pageable);
    }

    @Override
    public Animal update(Animal animal) {
        Optional<Animal> animalFromDb = this.animalRepo.findByNameAndSpeciesAndBreedAndGenderAndColor(animal.getName(), animal.getSpecies(), animal.getBreed(), animal.getGender(), animal.getColor());
        if (animalFromDb.isEmpty()) {
            throw new NotFoundException("Bu bilgilere sahip bir müşteri bulunamadı");
        }
        this.get(animal.getId());
        return this.animalRepo.save(animal);
    }

    @Override
    public boolean delete(Long id) {
        Animal animal = this.get(id);
        this.animalRepo.delete(animal);
        return true;
    }

    @Override
    public Animal findByAnimalName(String name) {

        Animal animal = this.animalRepo.findByName(name);
        if (animal == null) {
            throw new NoExistanceException(name + " bu isme ait bir bilgi bulunmamıştır.");
        }
        return this.animalRepo.findByName(name);
    }


}
