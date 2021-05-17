package com.sanvalero.cuartapracticaaccesodatosmayo.exception;

/**
 * Creado por @ author: Pedro Orós
 * el 17/05/2021
 */
public class PlateNotFoundException extends RuntimeException {

    public PlateNotFoundException() {
        super();
    }

    public PlateNotFoundException(String message){
        super(message);
    }

    public PlateNotFoundException(long id){
        super("Plate not found: " + id);
    }
}
