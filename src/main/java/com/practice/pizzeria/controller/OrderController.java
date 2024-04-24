package com.practice.pizzeria.controller;

import com.practice.pizzeria.persistence.entity.OrderEntity;
import com.practice.pizzeria.persistence.projection.OrderSummary;
import com.practice.pizzeria.service.OrderService;
import com.practice.pizzeria.service.dto.RamdomOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderEntity>> getAll(){
        return ResponseEntity.ok(this.orderService.getAll());
    }

    @GetMapping("/today")
    public ResponseEntity<List<OrderEntity>> getTodayOrders(){
        return ResponseEntity.ok(this.orderService.getTodayOrder());
    }

    @GetMapping("/outside")
    public ResponseEntity<List<OrderEntity>> getOutSide(){
        return ResponseEntity.ok(this.orderService.getOutsideOrders());
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<OrderEntity>> customer(@PathVariable String id){
        return ResponseEntity.ok(this.orderService.getCustomerOrders(id));
    }

@GetMapping("/summary/{id}")
    public ResponseEntity<OrderSummary> getSummary(@PathVariable int id){
        return ResponseEntity.ok(this.orderService.getSummary(id));
    }

    @PostMapping("/random")
    public ResponseEntity<Boolean> randomOrder(RamdomOrderDto dto){
        return ResponseEntity.ok(this.orderService.saveRamdomOrder(dto));
    }
}
