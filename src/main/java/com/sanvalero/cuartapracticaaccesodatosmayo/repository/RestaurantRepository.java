package com.sanvalero.cuartapracticaaccesodatosmayo.repository;

import com.sanvalero.cuartapracticaaccesodatosmayo.domain.Ingredient;
import com.sanvalero.cuartapracticaaccesodatosmayo.domain.Restaurant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 17/05/2021
 */

@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
    Set<Restaurant> findAll();

    @Query(value = "SELECT DISTINCT restaurant.* FROM restaurant " +
            "INNER JOIN plate ON restaurant.id = plate.restaurant_id " +
            "INNER JOIN ingredient ON plate.id = ingredient.plate_id " +
            "WHERE plate.price <= :price AND ingredient.vegan = :vegan", nativeQuery = true)
    Set<Restaurant> getRestaurantsByPriceAndVegan(float price, boolean vegan);
}
