package com.practice.pizzeria.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "order_item")
@IdClass(OrderItemId.class)
public class OrderItemEntity {
    @Id
    @Column(name = "id_order",nullable = false)
    private Integer idOrder;

    @Id
    @Column(name = "id_item",nullable = false)
    private Integer idItem;

    @Column(name = "id_pizza",nullable = false)
    private Integer idPizza;

    @Column(nullable = false,columnDefinition = "Decimal(2,1)")
    private Double quantity;

    @Column(nullable = false,columnDefinition = "Decimal(5,2)")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "id_order",referencedColumnName = "id_order",insertable = false,updatable = false)
    @JsonIgnore
    private OrderEntity order;

    @OneToOne
    @JoinColumn(name = "id_pizza",referencedColumnName = "id_pizza",insertable = false,updatable = false)
    private PizzaEntity pizza;


    public OrderItemEntity() {

    }
}
