package com.sanvalero.cuartapracticaaccesodatosmayo.exception;

/**
 * Creado por @ author: Pedro Orós
 * el 17/05/2021
 */
public class IngredientNotFoundException extends RuntimeException {

    public IngredientNotFoundException() {
        super();
    }

    public IngredientNotFoundException(String message){
        super(message);
    }

    public IngredientNotFoundException(long id){
        super("Ingredient not found: " + id);
    }
}
