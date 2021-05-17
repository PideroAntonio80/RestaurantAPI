package com.sanvalero.cuartapracticaaccesodatosmayo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * Creado por @ author: Pedro Orós
 * el 17/05/2021
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "ingredient")
public class Ingredient {

    @Schema(description = "Ingredient identification number", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Schema(description = "Ingredient name", example = "garlic", required = true)
    @NotBlank
    @Column
    private String name;

    @Schema(description = "Is this ingredient vegan?", example = "true")
    @NotBlank
    @Column
    private boolean vegan;

    @Schema(description = "Ingredient's plate identity")
    @ManyToOne
    @JoinColumn(name = "plate_id")
    @JsonBackReference
    private Plate plate;
}
