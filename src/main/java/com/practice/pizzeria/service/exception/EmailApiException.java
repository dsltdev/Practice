package com.practice.pizzeria.service.exception;

public class EmailApiException extends RuntimeException {

    public   EmailApiException() {
        super("Error al Enviar una notifiacion");
    }
}
