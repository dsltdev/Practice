package com.practice.pizzeria.controller;

import com.practice.pizzeria.persistence.entity.CustomerEntity;
import com.practice.pizzeria.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
@GetMapping("/phone/{phone}")
    public ResponseEntity<CustomerEntity> getByPhone(@PathVariable String phone){
        return ResponseEntity.ok(this.customerService.findByPhone(phone));
    }

}
