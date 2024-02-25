package dev.patika.VetManagement.business.abstracts;

import dev.patika.VetManagement.entities.Customer;

import org.springframework.data.domain.Page;

public interface ICustomerService {
    Customer save(Customer customer);
    Customer get(Long id);
    Page<Customer> cursor(int page, int pageSize);
    Customer update(Customer customer);
    boolean delete(Long id);
    Customer findByCustomerName(String name);
}
