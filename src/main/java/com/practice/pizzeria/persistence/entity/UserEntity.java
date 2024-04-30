package com.practice.pizzeria.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @Column(nullable = false,length = 25,unique = true)
    private String username;

    @Column(nullable = false,  length = 200)
    private String password;

    @Column(length = 30)
    private String email;

    @Column(nullable = false,columnDefinition = "TINYINT")
    private Boolean locked;

    @Column(nullable = false,columnDefinition = "TINYINT")
    private Boolean disable;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private List<UserRoleEntity> roles ;
}
