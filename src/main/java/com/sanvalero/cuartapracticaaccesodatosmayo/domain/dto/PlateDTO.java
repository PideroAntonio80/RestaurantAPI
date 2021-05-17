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
public class PlateDTO {

    @Schema(description = "Plate name", example = "chilly beans", required = true)
    private String name;

    @Schema(description = "Price plate", example = "20.5")
    private float price;
}
