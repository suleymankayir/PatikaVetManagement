package dev.patika.VetManagement.dao;

import dev.patika.VetManagement.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Long> {

    Customer findByName(String name);
    Optional<Customer> findByNameAndPhone(String name, String phone);
}
