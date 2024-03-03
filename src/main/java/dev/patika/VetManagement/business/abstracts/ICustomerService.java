package dev.patika.VetManagement.business.abstracts;

import dev.patika.VetManagement.dto.request.customer.CustomerSaveRequest;
import dev.patika.VetManagement.dto.request.customer.CustomerUpdateRequest;
import dev.patika.VetManagement.dto.response.customer.CustomerResponse;
import dev.patika.VetManagement.entities.Customer;
import org.springframework.data.domain.Page;

public interface ICustomerService {
    Customer save(Customer customer);

    Customer get(Long id);

    Page<Customer> cursor(int page, int pageSize);

    Customer update(Customer customer);

    boolean delete(Long id);

    Customer findByCustomerName(String name);

    Customer toCustomer(CustomerSaveRequest customerSaveRequest);

    CustomerResponse toResponse(Customer customer);

    Customer toCustomer(CustomerUpdateRequest customerUpdateRequest);
}
