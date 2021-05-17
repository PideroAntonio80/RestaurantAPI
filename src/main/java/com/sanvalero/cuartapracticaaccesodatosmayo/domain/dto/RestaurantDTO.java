package com.sanvalero.cuartapracticaaccesodatosmayo.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

/**
 * Creado por @ author: Pedro Orós
 * el 17/05/2021
 */

@Data
@NoArgsConstructor
public class RestaurantDTO {

    @Schema(description = "Restaurant name", example = "Plan", required = true)
    private String name;

    @Schema(description = "Restaurant opening date", example = "08/07/1994")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate openingDate;
}
