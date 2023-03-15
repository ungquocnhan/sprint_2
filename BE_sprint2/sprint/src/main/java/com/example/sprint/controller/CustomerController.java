package com.example.sprint.controller;

import com.example.sprint.dto.CustomerInfo;
import com.example.sprint.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/customers")
@CrossOrigin("*")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @GetMapping("{idCustomer}")
    public ResponseEntity<List<CustomerInfo>> getCustomer(@PathVariable("idCustomer") Integer id) {
        List<CustomerInfo> customerInfoList = customerService.getCustomer(id);
        return new ResponseEntity<>(customerInfoList, HttpStatus.OK);
    }
}
