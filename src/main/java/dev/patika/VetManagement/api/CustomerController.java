package dev.patika.VetManagement.api;

import dev.patika.VetManagement.business.abstracts.ICustomerService;
import dev.patika.VetManagement.core.config.modelMapper.IModelMapperService;
import dev.patika.VetManagement.core.result.Result;
import dev.patika.VetManagement.core.result.ResultData;
import dev.patika.VetManagement.core.utilities.ResultHelper;
import dev.patika.VetManagement.dto.request.customer.CustomerSaveRequest;
import dev.patika.VetManagement.dto.request.customer.CustomerUpdateRequest;
import dev.patika.VetManagement.dto.response.CursorResponse;
import dev.patika.VetManagement.dto.response.animal.AnimalResponse;
import dev.patika.VetManagement.dto.response.customer.CustomerResponse;
import dev.patika.VetManagement.entities.Animal;
import dev.patika.VetManagement.entities.Customer;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/customers")
public class CustomerController {


    private final ICustomerService customerService;

    private final IModelMapperService modelMapper;
    // Constructor injecting necessary services for customer management
    public CustomerController(ICustomerService customerService, IModelMapperService modelMapper) {
        this.customerService = customerService;
        this.modelMapper = modelMapper;
    }

    // Değerlendirme Formu - 10
    // Endpoint to create a new customer
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<CustomerResponse> save(@Valid @RequestBody CustomerSaveRequest customerSaveRequest) {
        Customer saveCustomer = this.modelMapper.forRequest().map(customerSaveRequest, Customer.class);
        this.customerService.save(saveCustomer);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveCustomer, CustomerResponse.class));

    }
    // Endpoint to retrieve a paginated list of customers
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<CustomerResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        Page<Customer> customerPage = this.customerService.cursor(page, pageSize);
        Page<CustomerResponse> customerResponsePage = customerPage
                .map(customer -> this.modelMapper.forResponse().map(customer, CustomerResponse.class));
        return ResultHelper.cursor(customerResponsePage);
    }
    // Endpoint to retrieve a customer by ID
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> get(@PathVariable("id") Long id) {
        Customer customer = this.customerService.get(id);
        CustomerResponse customerResponse = this.modelMapper.forResponse().map(customer, CustomerResponse.class);
        return ResultHelper.success(customerResponse);
    }
    // Endpoint to update an existing customer
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> update(@Valid @RequestBody CustomerUpdateRequest customerUpdateRequest) {
        this.customerService.get(customerUpdateRequest.getId());
        Customer updateCustomer = this.modelMapper.forRequest().map(customerUpdateRequest, Customer.class);
        this.customerService.update(updateCustomer);
        return ResultHelper.success(this.modelMapper.forResponse().map(updateCustomer, CustomerResponse.class));
    }
    // Endpoint to delete a customer by ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        this.customerService.delete(id);
        return ResultHelper.ok();
    }

    // Değerlendirme Formu - 17
    // Endpoint to retrieve a customer by name
    @GetMapping("/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> getCustomerByName(@PathVariable("name") String name) {
        Customer customer = this.customerService.findByCustomerName(name);


        return ResultHelper.success(this.modelMapper.forResponse().map(customer, CustomerResponse.class));
    }
    // Değerlendirme Formu - 18
    // Endpoint to retrieve animals owned by a customer
    @GetMapping("/{customerId}/animals")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AnimalResponse>> getAnimals(@PathVariable("customerId") Long customerId) {
        Customer customer = this.customerService.get(customerId);
        List<Animal> animals = customer.getAnimal();
        List<AnimalResponse> animalResponses = new ArrayList<>();

        for (Animal animal : animals) {
            animalResponses.add(this.modelMapper.forResponse().map(animal, AnimalResponse.class));
        }

        return ResultHelper.success(animalResponses);

    }


}
