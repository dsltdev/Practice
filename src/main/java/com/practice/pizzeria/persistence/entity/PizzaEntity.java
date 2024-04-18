package com.practice.pizzeria.persistence.entity;

import com.practice.pizzeria.persistence.audit.AuditPizzaListener;
import com.practice.pizzeria.persistence.audit.AuditableEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "pizza")
@EntityListeners({AuditingEntityListener.class, AuditPizzaListener.class})
public class PizzaEntity extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pizza", nullable = false)
    private Integer idPizza;

    @Column(nullable = false,length = 30,unique = true)
    private String name;

    @Column(nullable = false,length = 150)
    private String description;

    @Column(nullable = false,columnDefinition = "Decimal(5,2)")
    private Double price;

    @Column(columnDefinition = "TINYINT")
    private Boolean vegetarian;

    @Column(columnDefinition = "TINYINT",nullable = false)
    private Boolean available;

    @Column(nullable = false,columnDefinition = "CHAR(1)")
    private Boolean vegan;

    @Override
    public String toString() {
        return "PizzaEntity{" +
                "idPizza=" + idPizza +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", vegetarian=" + vegetarian +
                ", available=" + available +
                ", vegan=" + vegan +
                '}';
    }

    public PizzaEntity() {

    }
}
