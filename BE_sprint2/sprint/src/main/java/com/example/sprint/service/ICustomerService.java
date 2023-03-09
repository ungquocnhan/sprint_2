package com.example.sprint.service;

import com.example.sprint.dto.CustomerInfo;
import com.example.sprint.dto.GetIdCustomer;
import com.example.sprint.model.Customer;

import java.util.List;
import java.util.Optional;

public interface ICustomerService {
    Customer save(Customer customer);

    Optional<GetIdCustomer> findByEmail(String email);

    Optional<Customer> findById(Integer id);

    List<CustomerInfo> getCustomer(Integer id);
}
