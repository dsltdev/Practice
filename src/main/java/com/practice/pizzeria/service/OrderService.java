package com.practice.pizzeria.service;

import com.practice.pizzeria.persistence.entity.OrderEntity;
import com.practice.pizzeria.persistence.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderService {

    private static final String DELIVERY = "D";
    private static final String CARRYOUT = "C";
    private static final String DN_SITE = "S";

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderEntity> getAll(){
        return this.orderRepository.findAll();
}

    public  List<OrderEntity> getTodayOrder(){
        LocalDateTime today = LocalDateTime.now().toLocalDate().atTime(0,0);
        return this.orderRepository.findAllByDateAfter(today);
    }

    public List<OrderEntity> getOutsideOrders(){
        List<String> methods = Arrays.asList(DELIVERY, CARRYOUT);
        return this.orderRepository.findAllByMethodIn(methods);
    }

    public List<OrderEntity> getCustomerOrders(String idCustomer){
        return this.orderRepository.findCustomerOrders(idCustomer);
    }

}
