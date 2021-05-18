package com.sanvalero.cuartapracticaaccesodatosmayo.repository;

import com.sanvalero.cuartapracticaaccesodatosmayo.domain.Ingredient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 17/05/2021
 */

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
    Set<Ingredient> findAll();

    @Query(value = "SELECT ingredient.* FROM `ingredient` INNER JOIN plate ON plate.id = ingredient.plate_id WHERE plate.price <= :price AND ingredient.vegan = :vegan", nativeQuery = true)
    Set<Ingredient> getIngredientsByPriceAndVegan(float price, boolean vegan);
}
