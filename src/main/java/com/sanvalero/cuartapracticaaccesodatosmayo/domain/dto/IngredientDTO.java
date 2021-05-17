package com.sanvalero.cuartapracticaaccesodatosmayo.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

/**
 * Creado por @ author: Pedro Orós
 * el 17/05/2021
 */

@Data
@NoArgsConstructor
public class IngredientDTO {

    @Schema(description = "Ingredient name", example = "garlic", required = true)
    private String name;

    @Schema(description = "Is this ingredient vegan?", example = "true")
    private boolean vegan;
}
