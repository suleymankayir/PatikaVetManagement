package dev.patika.VetManagement.business.concretes;

import dev.patika.VetManagement.business.abstracts.ICustomerService;
import dev.patika.VetManagement.core.config.modelMapper.IModelMapperService;
import dev.patika.VetManagement.core.exception.EntityAlreadyExistException;
import dev.patika.VetManagement.core.exception.NoExistenceException;
import dev.patika.VetManagement.core.exception.NotFoundException;
import dev.patika.VetManagement.core.utilities.Msg;
import dev.patika.VetManagement.dao.CustomerRepo;
import dev.patika.VetManagement.dto.request.customer.CustomerSaveRequest;
import dev.patika.VetManagement.dto.request.customer.CustomerUpdateRequest;
import dev.patika.VetManagement.dto.response.customer.CustomerResponse;
import dev.patika.VetManagement.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerManager implements ICustomerService {

    private final CustomerRepo customerRepo;
    private final IModelMapperService modelMapper;

    public CustomerManager(CustomerRepo customerRepo, IModelMapperService modelMapper) {
        this.customerRepo = customerRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public Customer save(Customer customer) {
        Optional<Customer> customerFromDb = this.customerRepo.findByNameAndPhone(customer.getName(), customer.getPhone());
        if (customerFromDb.isPresent()) {
            throw new EntityAlreadyExistException(customerFromDb.get().getId(), Customer.class);
        }
        return this.customerRepo.save(customer);
    }

    @Override
    public Customer get(Long id) {
        return this.customerRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Page<Customer> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.customerRepo.findAll(pageable);
    }

    @Override
    public Customer update(Customer customer) {
        Optional<Customer> customerFromDb = this.customerRepo.findByNameAndPhone(customer.getName(), customer.getPhone());
        if (customerFromDb.isEmpty()) {
            throw new NotFoundException("Bu bilgilere sahip bir müşteri bulunamadı");
        }
        this.get(customer.getId());
        return this.customerRepo.save(customer);
    }

    @Override
    public boolean delete(Long id) {
        Customer customer = this.get(id);
        this.customerRepo.delete(customer);
        return true;
    }

    @Override
    public Customer findByCustomerName(String name) {

        Customer customer = this.customerRepo.findByName(name);
        if (customer == null) {
            throw new NoExistenceException(name + " bu isme ait bir bilgi bulunmamıştır.");
        }
        return customer;
    }

    @Override
    public Customer toCustomer(CustomerSaveRequest customerSaveRequest) {
        return this.modelMapper.forRequest().map(customerSaveRequest, Customer.class);
    }

    @Override
    public CustomerResponse toResponse(Customer customer) {
        return this.modelMapper.forResponse().map(customer, CustomerResponse.class);
    }

    @Override
    public Customer toCustomer(CustomerUpdateRequest customerUpdateRequest) {
        return this.modelMapper.forRequest().map(customerUpdateRequest, Customer.class);
    }
}
