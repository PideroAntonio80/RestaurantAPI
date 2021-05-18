package com.sanvalero.cuartapracticaaccesodatosmayo.service;

import com.sanvalero.cuartapracticaaccesodatosmayo.domain.Ingredient;
import com.sanvalero.cuartapracticaaccesodatosmayo.domain.Plate;
import com.sanvalero.cuartapracticaaccesodatosmayo.domain.dto.IngredientDTO;
import com.sanvalero.cuartapracticaaccesodatosmayo.exception.IngredientNotFoundException;
import com.sanvalero.cuartapracticaaccesodatosmayo.exception.PlateNotFoundException;
import com.sanvalero.cuartapracticaaccesodatosmayo.repository.IngredientRepository;
import com.sanvalero.cuartapracticaaccesodatosmayo.repository.PlateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 17/05/2021
 */

@Service
public class IngredientServiceImp implements IngredientService{

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private PlateRepository plateRepository;

    @Override
    public Set<Ingredient> findAll() {
        return ingredientRepository.findAll();
    }

    @Override
    public Optional<Ingredient> findById(long id) {
        return ingredientRepository.findById(id);
    }

    @Override
    public Set<Ingredient> getIngredientsByPriceAndVegan(float price, boolean vegan) {
        return ingredientRepository.getIngredientsByPriceAndVegan(price, vegan);
    }

    @Override
    public Ingredient addIngredientToPlate(long id, IngredientDTO ingredientDTO) {
        Ingredient newIngredient = new Ingredient();
        setIngredient(newIngredient, ingredientDTO);
        newIngredient = ingredientRepository.save(newIngredient);
        Plate plate = plateRepository.findById(id)
                .orElseThrow(() -> new PlateNotFoundException(id));
        newIngredient.setPlate(plate);

        ingredientRepository.save(newIngredient);

        return newIngredient;
    }

    @Override
    public Ingredient modifyIngredient(long id, IngredientDTO ingredientDTO) {
        Ingredient ingredient = ingredientRepository.findById(id)
                .orElseThrow(() -> new IngredientNotFoundException(id));
        setIngredient(ingredient, ingredientDTO);
        return ingredientRepository.save(ingredient);
    }

    @Override
    public void deleteIngredient(long id) {
        Ingredient ingredient = ingredientRepository.findById(id)
                .orElseThrow(() -> new IngredientNotFoundException(id));
        ingredientRepository.delete(ingredient);
    }

    public void setIngredient(Ingredient ingredient, IngredientDTO ingredientDTO) {
        ingredient.setName(ingredientDTO.getName());
        ingredient.setVegan(ingredientDTO.isVegan());
    }
}
