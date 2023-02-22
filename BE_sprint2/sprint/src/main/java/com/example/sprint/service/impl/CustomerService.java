package com.example.sprint.service.impl;

import com.example.sprint.model.Customer;
import com.example.sprint.repository.ICustomerRepository;
import com.example.sprint.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }
}
