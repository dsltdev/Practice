package com.practice.pizzeria.service;

import com.practice.pizzeria.persistence.repository.PizzaPagSortRepository;
import com.practice.pizzeria.persistence.repository.PizzaRepository;
import com.practice.pizzeria.persistence.entity.PizzaEntity;
import com.practice.pizzeria.service.dto.updatePizzaPriceDto;
import com.practice.pizzeria.service.exception.EmailApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PizzaService {

    private final PizzaRepository pizzaRepository;

    private final PizzaPagSortRepository pizzaPagSortRepository;


    @Autowired
    public PizzaService(PizzaRepository pizzaRepository, PizzaPagSortRepository pizzaPagSortRepository) {
        this.pizzaRepository = pizzaRepository;
        this.pizzaPagSortRepository = pizzaPagSortRepository;
    }

    public Page<PizzaEntity> getAll(int page , int elements){
        Pageable pageRequest = PageRequest.of(page,elements);
        return this.pizzaPagSortRepository.findAll(pageRequest);
    }

    public Page<PizzaEntity> getAvailble(int page , int elements,String sortBy,String sortDirection){
        Pageable pageRequest = PageRequest.of(page,elements, Sort.by(sortBy));
        //Sort sort = Sort.by(Sort.Direction.fromString(sortDirection),sortBy);
        return this.pizzaPagSortRepository.findByAvailableTrue(pageRequest);
    }


    public PizzaEntity getByName(String name ){
        return this.pizzaRepository.findFirstByAvailableTrueAndNameIgnoreCase(name).orElseThrow(() -> new RuntimeException("La Pizza no existe"));
    }

    public List<PizzaEntity> getWith (String ingredient ){
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionContainsIgnoreCase(ingredient);
    }
    public List<PizzaEntity> getWithOut (String ingredient ){
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionNotContainsIgnoreCase(ingredient);
    }

    public List<PizzaEntity> getCheaters(double price ){
        return this.pizzaRepository.findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(price);
    }


    public  PizzaEntity get(int idPizza){
        return pizzaRepository.findById(idPizza).orElse(null);
    }

    public PizzaEntity save (PizzaEntity pizza){
        return this.pizzaRepository.save(pizza);
    }

    public  Boolean exist (int idPizza){
         return this.pizzaRepository.existsById(idPizza);
   }

   public void delete(int idPizza){
        this.pizzaRepository.deleteAll();
   }

   @Transactional(noRollbackFor = EmailApiException.class,
   propagation = Propagation.REQUIRED)
   public void updatePrice(updatePizzaPriceDto dto){
      this.pizzaRepository.updatePrice(dto);
      this.sedEmail();
   }


   public  void sedEmail(){
    throw new EmailApiException();
   }
}
