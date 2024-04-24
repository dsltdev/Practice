package com.practice.pizzeria.persistence.repository;

import com.practice.pizzeria.persistence.entity.PizzaEntity;
import com.practice.pizzeria.service.dto.updatePizzaPriceDto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity,Integer> {
   /// List<PizzaEntity>findAllByAvailableTrueOrderByPrice();
    Optional<PizzaEntity> findFirstByAvailableTrueAndNameIgnoreCase(String name);

    List<PizzaEntity> findAllByAvailableTrueAndDescriptionContainsIgnoreCase(String description);

    List<PizzaEntity> findAllByAvailableTrueAndDescriptionNotContainsIgnoreCase(String description);

    List<PizzaEntity> findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(double price);

    @Query(value =
             "UPDATE pizza " +
             "SET price = :#{#newPizzaPrice.newPrice } " +
             "WHERE id_pizza = :#{#newPizzaPrice.pizzaId } ", nativeQuery = true)
    @Modifying
    void updatePrice (@Param ("newPizzaPrice") updatePizzaPriceDto newPizzaPrice);

}
