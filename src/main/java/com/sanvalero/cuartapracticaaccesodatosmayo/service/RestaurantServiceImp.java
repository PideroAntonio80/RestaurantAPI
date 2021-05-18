package com.sanvalero.cuartapracticaaccesodatosmayo.service;

import com.sanvalero.cuartapracticaaccesodatosmayo.domain.Ingredient;
import com.sanvalero.cuartapracticaaccesodatosmayo.domain.Restaurant;
import com.sanvalero.cuartapracticaaccesodatosmayo.domain.dto.RestaurantDTO;
import com.sanvalero.cuartapracticaaccesodatosmayo.exception.RestaurantNotFoundException;
import com.sanvalero.cuartapracticaaccesodatosmayo.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 17/05/2021
 */

@Service
public class RestaurantServiceImp implements RestaurantService{

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public Set<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    @Override
    public Optional<Restaurant> findById(long id) {
        return restaurantRepository.findById(id);
    }

    @Override
    public Set<Restaurant> getRestaurantsByPriceAndVegan(float price, boolean vegan) {
        return restaurantRepository.getRestaurantsByPriceAndVegan(price, vegan);
    }

    @Override
    public Restaurant addRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant modifyRestaurant(long id, RestaurantDTO restaurantDTO) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));
        return restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurant(long id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));
        restaurantRepository.delete(restaurant);
    }

    public void setRestaurant(Restaurant restaurant, RestaurantDTO restaurantDTO) {
        restaurant.setName(restaurantDTO.getName());
        restaurant.setOpeningDate(restaurantDTO.getOpeningDate());
    }
}
