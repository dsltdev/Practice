package com.practice.pizzeria.controller;

import com.practice.pizzeria.persistence.entity.PizzaEntity;
import com.practice.pizzeria.service.PizzaService;
import com.practice.pizzeria.service.dto.updatePizzaPriceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {


    private final PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService pizzaService){
        this.pizzaService = pizzaService;
    }

    @GetMapping
    public ResponseEntity<Page<PizzaEntity>> getAll(@RequestParam(defaultValue = "0")int page,
                                                    @RequestParam(defaultValue = "8")int elements){
        return  ResponseEntity.ok(this.pizzaService.getAll(page, elements));
    }

    @GetMapping("/available")
    public ResponseEntity<Page<PizzaEntity>> get(@RequestParam(defaultValue = "0")int page,
                                                 @RequestParam(defaultValue = "8")int elements,
                                                 @RequestParam(defaultValue = "price") String sortBy,
                                                 @RequestParam(defaultValue = "ASC") String sortDirection){
        return  ResponseEntity.ok(this.pizzaService.getAvailble(page, elements, sortBy,sortDirection));
    }

    @GetMapping("/{idPizza}")
    public ResponseEntity<PizzaEntity> getAvailable(@PathVariable int idPizza){
        return  ResponseEntity.ok(this.pizzaService.get(idPizza));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<PizzaEntity> getByName(@PathVariable String name){
        return  ResponseEntity.ok(this.pizzaService.getByName(name));
    }

    @GetMapping("/with/{ingredint}")
    public ResponseEntity<List<PizzaEntity>> getWith (@PathVariable String ingredint){
        return  ResponseEntity.ok(this.pizzaService.getWith(ingredint));
    }

    @GetMapping("/without/{ingredint}")
    public ResponseEntity<List<PizzaEntity>> getWithOut (@PathVariable String ingredint){
        return  ResponseEntity.ok(this.pizzaService.getWithOut(ingredint));
    }


    @PostMapping
    public ResponseEntity<PizzaEntity> add (@RequestBody PizzaEntity pizza){
        if (pizza.getIdPizza() == null || this.pizzaService.exist(pizza.getIdPizza())){
            return ResponseEntity.ok(this.pizzaService.save(pizza));
        }
      return ResponseEntity.badRequest().build();
    }



    @DeleteMapping("/{idPizza}")
    public ResponseEntity<Void> delete (@PathVariable int idPizza){
        if (this.pizzaService.exist(idPizza)){
            this.pizzaService.delete(idPizza);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping
    public ResponseEntity<PizzaEntity> update (@RequestBody PizzaEntity pizza){
        if (pizza.getIdPizza() != null && this.pizzaService.exist(pizza.getIdPizza())){
            return ResponseEntity.ok(this.pizzaService.save(pizza));
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/cheapets/{price}")
    public ResponseEntity<List<PizzaEntity>> getWithOut (@PathVariable double price){
        return  ResponseEntity.ok(this.pizzaService.getCheaters(price));
    }


    @PutMapping("/update/prices")
    public ResponseEntity<Void> update (@RequestBody updatePizzaPriceDto dto){
        if (this.pizzaService.exist(dto.getPizzaId())){
            this.pizzaService.updatePrice(dto);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

}
