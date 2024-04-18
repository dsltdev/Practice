package com.practice.pizzeria.persistence.repository;

import com.practice.pizzeria.persistence.entity.PizzaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface PizzaPagSortRepository extends ListPagingAndSortingRepository<PizzaEntity,Integer> {

    Page<PizzaEntity> findByAvailableTrue(Pageable pageable);
}
