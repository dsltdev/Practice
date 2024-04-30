package com.practice.pizzeria.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user_role")
@IdClass(UserRoleId.class)
public class UserRoleEntity {

    @Column(nullable = false,length = 20)
    private String username;

    @Id
    @Column(nullable = false,length = 20)
    private String role;

    @Column(name = "granted_date",nullable = false,columnDefinition = "DATETIME")
    private LocalDateTime grantedDate;

    @ManyToOne
    @JoinColumn(name = "username" ,referencedColumnName = "username",insertable = false)
    private UserEntity user;
}
