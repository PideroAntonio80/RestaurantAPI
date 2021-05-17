package com.sanvalero.cuartapracticaaccesodatosmayo.service;

import com.sanvalero.cuartapracticaaccesodatosmayo.domain.Plate;
import com.sanvalero.cuartapracticaaccesodatosmayo.domain.Restaurant;
import com.sanvalero.cuartapracticaaccesodatosmayo.domain.dto.PlateDTO;
import com.sanvalero.cuartapracticaaccesodatosmayo.exception.PlateNotFoundException;
import com.sanvalero.cuartapracticaaccesodatosmayo.exception.RestaurantNotFoundException;
import com.sanvalero.cuartapracticaaccesodatosmayo.repository.PlateRepository;
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
public class PlateServiceImp implements PlateService {

    @Autowired
    private PlateRepository plateRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;
    
    @Override
    public Set<Plate> findAll() {
        return plateRepository.findAll();
    }

    @Override
    public Optional<Plate> findById(long id) {
        return plateRepository.findById(id);
    }

    @Override
    public Plate addPlateToRestaurant(long id, PlateDTO plateDTO) {
        Plate newPlate = new Plate();
        setPlate(newPlate, plateDTO);
        newPlate = plateRepository.save(newPlate);
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));
        newPlate.setRestaurant(restaurant);

        plateRepository.save(newPlate);

        return newPlate;
    }

    @Override
    public Plate modifyPlate(long id, PlateDTO plateDTO) {
        Plate plate = plateRepository.findById(id)
                .orElseThrow(() -> new PlateNotFoundException(id));
        setPlate(plate, plateDTO);
        return plateRepository.save(plate);
    }

    @Override
    public void deletePlate(long id) {
        Plate plate = plateRepository.findById(id)
                .orElseThrow(() -> new PlateNotFoundException(id));
        plateRepository.delete(plate);
    }

    public void setPlate(Plate plate, PlateDTO plateDTO) {
        plate.setName(plateDTO.getName());
        plate.setPrice(plateDTO.getPrice());
    }
}
