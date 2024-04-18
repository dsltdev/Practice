package com.practice.pizzeria.service;

import com.practice.pizzeria.persistence.entity.CustomerEntity;
import com.practice.pizzeria.persistence.repository.CustomerRepository;
import org.hibernate.annotations.SecondaryRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerEntity findByPhone(String phone){
        return this.customerRepository.findByPhoneNumber(phone);
    }
}
