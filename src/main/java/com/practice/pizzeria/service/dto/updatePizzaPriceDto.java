package com.practice.pizzeria.service.dto;

import lombok.Data;

@Data
public class updatePizzaPriceDto {

    private int pizzaId;
    private double newPrice;
}
