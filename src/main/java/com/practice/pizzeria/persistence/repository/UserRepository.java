package com.practice.pizzeria.persistence.repository;

import com.practice.pizzeria.persistence.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity,String> {
}
