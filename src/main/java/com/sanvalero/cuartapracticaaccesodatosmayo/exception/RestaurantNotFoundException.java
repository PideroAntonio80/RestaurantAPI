package com.sanvalero.cuartapracticaaccesodatosmayo.exception;

/**
 * Creado por @ author: Pedro Orós
 * el 17/05/2021
 */
public class RestaurantNotFoundException extends RuntimeException {

    public RestaurantNotFoundException() {
        super();
    }

    public RestaurantNotFoundException(String message){
        super(message);
    }

    public RestaurantNotFoundException(long id){
        super("Restaurant not found: " + id);
    }
}
