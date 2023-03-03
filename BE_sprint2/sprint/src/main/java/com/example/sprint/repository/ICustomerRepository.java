package com.example.sprint.repository;

import com.example.sprint.dto.GetIdCustomer;
import com.example.sprint.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ICustomerRepository extends JpaRepository<Customer, Integer> {
    @Query(value = "select id as idCustomer from customer where email = :email", nativeQuery = true)
    Optional<GetIdCustomer> findByEmail(String email);

    Optional<Customer> findById(Integer id);
}
