package dev.patika.VetManagement.business.concretes;

import dev.patika.VetManagement.business.abstracts.ICustomerService;
import dev.patika.VetManagement.core.exception.EntityAlreadyExistException;
import dev.patika.VetManagement.core.exception.NoExistanceException;
import dev.patika.VetManagement.core.exception.NotFoundException;
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
            throw new NoExistanceException(name + " bu isme ait bir bilgi bulunmamıştır.");
        }
        return customer;
    }
}
