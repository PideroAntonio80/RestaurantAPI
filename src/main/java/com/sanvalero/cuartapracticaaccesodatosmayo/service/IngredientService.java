package com.sanvalero.cuartapracticaaccesodatosmayo.service;

import com.sanvalero.cuartapracticaaccesodatosmayo.domain.Ingredient;
import com.sanvalero.cuartapracticaaccesodatosmayo.domain.dto.IngredientDTO;

import java.util.Optional;
import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 17/05/2021
 */

public interface IngredientService {

    Set<Ingredient> findAll();
    Optional<Ingredient> findById(long id);

    Ingredient addIngredientToPlate(long id, IngredientDTO ingredientDTO);
    Ingredient modifyIngredient(long id, IngredientDTO ingredientDTO);
    void deleteIngredient(long id);
}
