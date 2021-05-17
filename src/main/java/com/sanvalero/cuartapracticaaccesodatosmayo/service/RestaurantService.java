package com.sanvalero.cuartapracticaaccesodatosmayo.service;

import com.sanvalero.cuartapracticaaccesodatosmayo.domain.Restaurant;
import com.sanvalero.cuartapracticaaccesodatosmayo.domain.dto.RestaurantDTO;

import java.util.Optional;
import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 17/05/2021
 */

public interface RestaurantService {

    Set<Restaurant> findAll();
    Optional<Restaurant> findById(long id);

    Restaurant addRestaurant(Restaurant restaurant);
    Restaurant modifyRestaurant(long id, RestaurantDTO restaurantDTO);
    void deleteRestaurant(long id);
}
