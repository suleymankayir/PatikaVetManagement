package dev.patika.VetManagement.business.concretes;

import dev.patika.VetManagement.business.abstracts.ICustomerService;
import dev.patika.VetManagement.core.exception.CustomerAlreadyExistException;
import dev.patika.VetManagement.core.exception.NotFoundException;
import dev.patika.VetManagement.core.result.Result;
import dev.patika.VetManagement.core.utilities.Msg;
import dev.patika.VetManagement.dao.CustomerRepo;
import dev.patika.VetManagement.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerManager implements ICustomerService {

    private final CustomerRepo customerRepo;

    public CustomerManager(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @Override
    public Customer save(Customer customer) {
        Optional<Customer> customerFromDb = customerRepo.findByNameAndPhone(customer.getName(), customer.getPhone());
        if (customerFromDb.isPresent()) {
            throw new CustomerAlreadyExistException(customerFromDb.get().getId());
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
        return this.customerRepo.findByName(name);
    }
}
