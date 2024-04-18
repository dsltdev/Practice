package com.practice.pizzeria.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "customer")
public class CustomerEntity {
    @Id
    @Column(name = "id_customer",nullable = false,length = 15)
    private String idCustomer;

    @Column(nullable = false,length = 60)
    private String name;

    @Column(length = 100)
    private String address;

    @Column(nullable = false,length = 50,unique = true)
    private String email;

    @Column(name = "phone_number",length = 20)
    private String phoneNumber;

    public CustomerEntity() {

    }
}
