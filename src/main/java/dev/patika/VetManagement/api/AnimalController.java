package dev.patika.VetManagement.api;

import dev.patika.VetManagement.business.abstracts.IAnimalService;
import dev.patika.VetManagement.business.abstracts.ICustomerService;
import dev.patika.VetManagement.core.result.Result;
import dev.patika.VetManagement.core.result.ResultData;
import dev.patika.VetManagement.core.utilities.ResultHelper;
import dev.patika.VetManagement.dto.request.animal.AnimalSaveRequest;
import dev.patika.VetManagement.dto.request.animal.AnimalUpdateRequest;
import dev.patika.VetManagement.dto.response.CursorResponse;
import dev.patika.VetManagement.dto.response.animal.AnimalResponse;
import dev.patika.VetManagement.entities.Animal;
import dev.patika.VetManagement.entities.Customer;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/animals")
public class AnimalController {

    private final IAnimalService animalService;
    private final ICustomerService customerService;

    // Constructor injecting necessary services for animal management
    public AnimalController(IAnimalService animalService, ICustomerService customerService) {
        this.animalService = animalService;
        this.customerService = customerService;
    }

    // Değerlendirme Formu - 11
    // Endpoint to create a new animal
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AnimalResponse> save(@Valid @RequestBody AnimalSaveRequest animalSaveRequest) {
        Customer customer = this.customerService.get(animalSaveRequest.getCustomerId());
        animalSaveRequest.setCustomerId(0L);

        Animal saveAnimal = this.animalService.toAnimal(animalSaveRequest);
        saveAnimal.setCustomer(customer);

        this.animalService.save(saveAnimal);
        return ResultHelper.created(this.animalService.toResponse(saveAnimal));

    }

    // Endpoint to retrieve an animal by ID
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> get(@PathVariable("id") Long id) {
        Animal animal = this.animalService.get(id);
        AnimalResponse animalResponse = this.animalService.toResponse(animal);
        return ResultHelper.success(animalResponse);
    }

    // Endpoint to retrieve a paginated list of animals
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AnimalResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize) {
        Page<Animal> animalPage = this.animalService.cursor(page, pageSize);
        Page<AnimalResponse> animalResponsePage = animalPage
                .map(this.animalService::toResponse);
        return ResultHelper.cursor(animalResponsePage);
    }

    // Endpoint to update an existing animal
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> update(@Valid @RequestBody AnimalUpdateRequest animalUpdateRequest) {
        this.animalService.get(animalUpdateRequest.getId());
        Animal updateAnimal = this.animalService.toAnimal(animalUpdateRequest);

        this.animalService.update(updateAnimal);
        return ResultHelper.success(this.animalService.toResponse(updateAnimal));
    }

    // Endpoint to delete an animal by ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        this.animalService.delete(id);
        return ResultHelper.ok();
    }


    // Değerlendirme Formu - 16
    // Endpoint to retrieve an animal by name
    @GetMapping("/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> getAnimalByName(@PathVariable("name") String name) {
        Animal animal = this.animalService.findByAnimalName(name);
        return ResultHelper.success(this.animalService.toResponse(animal));
    }

}
