package com.sanvalero.cuartapracticaaccesodatosmayo.repository;

import com.sanvalero.cuartapracticaaccesodatosmayo.domain.Plate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 17/05/2021
 */

@Repository
public interface PlateRepository extends CrudRepository<Plate, Long> {
    Set<Plate> findAll();
}
