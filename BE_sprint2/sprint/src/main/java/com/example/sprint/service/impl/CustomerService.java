package com.example.sprint.service.impl;

import com.example.sprint.dto.GetIdCustomer;
import com.example.sprint.model.Customer;
import com.example.sprint.repository.ICustomerRepository;
import com.example.sprint.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Optional<GetIdCustomer> findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    @Override
    public Optional<Customer> findById(Integer id) {
        return customerRepository.findById(id);
    }
}
