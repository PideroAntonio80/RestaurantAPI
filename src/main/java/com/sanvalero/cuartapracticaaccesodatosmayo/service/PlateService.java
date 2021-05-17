package com.sanvalero.cuartapracticaaccesodatosmayo.service;

import com.sanvalero.cuartapracticaaccesodatosmayo.domain.Plate;
import com.sanvalero.cuartapracticaaccesodatosmayo.domain.dto.PlateDTO;

import java.util.Optional;
import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 17/05/2021
 */

public interface PlateService {

    Set<Plate> findAll();
    Optional<Plate> findById(long id);

    Plate addPlateToRestaurant(long id, PlateDTO plateDTO);
    Plate modifyPlate(long id, PlateDTO plateDTO);
    void deletePlate(long id);
}
